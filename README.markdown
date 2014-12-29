# Symantic Versioning for Gradle

This plugin is an implementation of [Symantic Versioning](http://semver.org/). 

> In the world of software management there exists a dread place called "dependency hell." The bigger your system grows and the more packages you integrate into your software, the more likely you are to find yourself, one day, in this pit of despair.
> 
> In systems with many dependencies, releasing new package versions can quickly become a nightmare. If the dependency specifications are too tight, you are in danger of version lock (the inability to upgrade a package without having to release new versions of every dependent package). If dependencies are specified too loosely, you will inevitably be bitten by version promiscuity (assuming compatibility with more future versions than is reasonable). Dependency hell is where you are when version lock and/or version promiscuity prevent you from easily and safely moving your project forward.

## Usage 

To use it all you need to do is

    apply plugin: 'com.github.ethankhall.semantic-versioning'

To set the version do 

    project.version.with { major = 1; minor= 2; patch = 3}

That input will create the version string "1.2.3-SNAPSHOT" by default. If you add the 'release' task to your build the version string will become "1.2.3".

If you need to add a pre-release version this plugin does this too!

    project.version.with { major = 2; minor= 3; patch = 4; preRelease = "beta1"}

With that input you will get the version string "1.2.3-beta1-SNAPSHOT".

If you want your build to be set as a release. Then you will set 'releaseBuild' property in the version.

    project.version.with { major = 2; minor= 3; patch = 4; preRelease = "beta1"; releaseBuild = true }

With the releaseBuild set to true the version string will be "1.2.3-beta1". The releaseBuild setting is a good place to put logic around if a build should be a release or not (based on branch or environment variable for example).

### What numbers should I update?

> Given a version number MAJOR.MINOR.PATCH, increment the:
> 
> MAJOR version when you make incompatible API changes,
> MINOR version when you add functionality in a backwards-compatible manner, and
> PATCH version when you make backwards-compatible bug fixes.
> Additional labels for pre-release and build metadata are available as extensions to the MAJOR.MINOR.PATCH format.

This plugin takes a lot of influence from another plugin at [gradle-semver-plugin](https://github.com/foo4u/gradle-semver-plugin/blob/master/README.md)
