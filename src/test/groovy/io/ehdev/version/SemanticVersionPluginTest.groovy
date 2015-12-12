package io.ehdev.version;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test

import static org.fest.assertions.Assertions.assertThat;

public class SemanticVersionPluginTest {
    private Project project;

    @BeforeMethod
    public void setUp() throws Exception {
        project = ProjectBuilder.builder().build();
    }

    @Test
    public void testThatWhenAppliedHasVersionSet() throws Exception {
        project.apply plugin: 'com.github.ethankhall.semantic-versioning'
        assertThat(project.version).isInstanceOf(Version.class)
    }

    @Test
    public void testThatWhenReleaseNotCalledThenWillHaveSNAPSHOTAtTheEndOfTheVersionString() throws Exception {
        project.apply plugin: 'com.github.ethankhall.semantic-versioning'
        project.version.with { major = 1; minor= 2; patch = 3}
        assertThat(project.version.toString()).isEqualToIgnoringCase("1.2.3-SNAPSHOT")
        project.version.with { major = 2; minor= 3; patch = 4; preRelease = "beta1"}
        assertThat(project.version.toString()).isEqualToIgnoringCase("2.3.4-beta1-SNAPSHOT")
    }

    @Test
    public void testThatAfterReleaseBuildIsSetWillNotHaveSnapshot() throws Exception {
        project.apply plugin: 'com.github.ethankhall.semantic-versioning'
        project.version.with { major = 1; minor= 2; patch = 3; releaseBuild=true}
        assertThat(project.version.toString()).isEqualToIgnoringCase("1.2.3")
    }
    
    @Test
    public void testThatAfterReleaseTaskIsCalledWillNotHaveSnapshot() throws Exception {
        project.apply plugin: 'com.github.ethankhall.semantic-versioning'
        project.version.with { major = 1; minor= 2; patch = 3}
        assertThat(project.version.toString()).isEqualToIgnoringCase("1.2.3-SNAPSHOT")
        def releaseTask = project.tasks.findByName('release')
        releaseTask.actions.each {action -> action.execute(releaseTask)}
        assertThat(project.version.toString()).isEqualToIgnoringCase("1.2.3")
    }
    
}