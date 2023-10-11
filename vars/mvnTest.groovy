def call() {
    def mvnCommand = 'mvn test'
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven test failed with exit code: $exitCode"
    }
}