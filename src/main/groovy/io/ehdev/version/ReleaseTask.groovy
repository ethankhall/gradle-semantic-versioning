package io.ehdev.version

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ReleaseTask extends DefaultTask {

    @TaskAction
    def markBuildAsRelease() {
        Version.releaseBuild = true;
    }
}
