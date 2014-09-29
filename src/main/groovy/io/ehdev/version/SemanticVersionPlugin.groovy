package io.ehdev.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.internal.artifact.DefaultMavenArtifact
import org.gradle.api.publish.maven.internal.publisher.MavenProjectIdentity
import org.gradle.api.publish.maven.tasks.GenerateMavenPom
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository

class SemanticVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.setVersion(new Version())

        project.task(type: ReleaseTask, "release") {
            description = "Make the build a release version"
            group = "build"
        }

        project.gradle.taskGraph.whenReady { taskGraph ->
            project.version.releaseBuild = taskGraph.hasTask(':release')

            project.tasks.withType(PublishToMavenRepository) { publishTask ->
                def newArts = publishTask.publication.getArtifacts().collect {art ->
                    def newPath = art.getFile().getPath().replace(publishTask.publication.version, project.version.toString())
                    def file = new File(newPath)
                    return [classifier: art.classifier, extension: art.extension, file: file]
                }
                publishTask.publication.getArtifacts().clear();
                newArts.each {
                    publishTask.publication.getArtifacts().add(new DefaultMavenArtifact(it.file, it.extension, it.classifier))
                }

                publishTask.publication.version = project.version
            }

            project.tasks.withType(GenerateMavenPom) { GenerateMavenPom mavenPom ->
                ((MavenProjectIdentity)mavenPom.pom.getProjectIdentity()).version = project.version
            }
        }
    }
}
