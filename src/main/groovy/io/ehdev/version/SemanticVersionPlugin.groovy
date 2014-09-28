package io.ehdev.version

import org.gradle.api.Plugin
import org.gradle.api.Project

class SemanticVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.setVersion(new Version())

        project.task(type: ReleaseTask, "release") {
            description = "Make the build a release version"
            group = "build"
        }
    }
}
