package io.ehdev.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.internal.publisher.MavenProjectIdentity
import org.gradle.api.publish.maven.tasks.GenerateMavenPom
import org.gradle.api.publish.maven.tasks.PublishToMavenLocal
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
                publishTask.publication.version = project.version
            }

            project.tasks.withType(PublishToMavenLocal) { publishTask ->
                publishTask.publication.version = project.version
            }

            project.tasks.withType(GenerateMavenPom) { GenerateMavenPom mavenPom ->
                ((MavenProjectIdentity)mavenPom.pom.getProjectIdentity()).version = project.version
            }
        }
    }
}
