def call() {
    def mvnCommand = 'mvn clean install -Dmaven.checkstyle.skip=true'
    def exitCode = sh(script: mvnCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Maven build failed with exit code: $exitCode"
    }
}
