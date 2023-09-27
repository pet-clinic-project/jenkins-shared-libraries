def call() {
    def mvnCommand = './mvnw package -Dcheckstyle.skip'
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven build failed with exit code: $exitCode"
    }
}
