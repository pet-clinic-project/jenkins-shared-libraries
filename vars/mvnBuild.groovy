def call() {
    def mvnCommand = 'mvn clean install -Dcheckstyle.skip'
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven build failed with exit code: $exitCode"
    }
}
