def call(String nexusUrl, String nexusRepository, String nexusCredentialsId, String jarFileName) {
    def credentials = credentials(nexusCredentialsId)
    def nexusUsername = credentials.username
    def nexusPassword = credentials.password

    def jarFile = findFiles(glob: 'target/*.jar').first()

    def uploadCommand = "curl -v -u ${nexusUsername}:${nexusPassword} --upload-file ${jarFile} ${nexusUrl}/${nexusRepository}/${jarFile.name}"
    def exitCode = sh(script: uploadCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Failed to upload JAR file to Nexus. Exit code: ${exitCode}"
    }
}
