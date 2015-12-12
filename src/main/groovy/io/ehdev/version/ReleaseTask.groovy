package io.ehdev.version

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ReleaseTask extends DefaultTask {
    public ReleaseTask() {
        description = "Make the build a release version"  
        group = "build"  
    }

    @TaskAction
    def markBuildAsRelease() {
        project.version.releaseBuild = true;
    }
}
