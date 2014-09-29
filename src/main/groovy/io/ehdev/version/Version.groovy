package io.ehdev.version

class Version {
    int major, minor, patch
    String preRelease
    boolean releaseBuild = false;

    String toString() {
        def versionString = "${major}.${minor}.${patch}";
        if(preRelease){
            versionString += "-${preRelease}"
        }
        if(!releaseBuild || System.getProperty("release")?.equalsIgnoreCase("true")) {
            versionString += "-SNAPSHOT"
        }
        return versionString;
    }
}
