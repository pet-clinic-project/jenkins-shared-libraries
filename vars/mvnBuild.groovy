def runMavenBuild() {
    def mvnCommand = 'mvn clean package -U'
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven build failed with exit code: $exitCode"
    }
}
