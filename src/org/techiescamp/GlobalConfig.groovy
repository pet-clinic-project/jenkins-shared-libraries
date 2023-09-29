package org.techiescamp

class GlobalConfig {
    static String versionTag = "1.0.${System.getenv('BUILD_NUMBER')}"
}