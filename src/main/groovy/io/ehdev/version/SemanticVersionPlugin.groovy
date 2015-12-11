package io.ehdev.version
import org.gradle.api.Plugin
import org.gradle.api.Project

class SemanticVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.setVersion(new Version())
        project.tasks.create('release', ReleaseTask)
    }
}
