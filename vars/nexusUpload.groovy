def call(String nexusUrl, String nexusRepository, String nexusCredentialsId, String jarFileName) {
    def credentials = credentials(nexusCredentialsId)
    def nexusUsername = credentials.username
    def nexusPassword = credentials.password

    def jarFile = findFiles(glob: 'target/*.jar').first()

    sh """
        curl -v -u ${nexusUsername}:${nexusPassword} --upload-file ${jarFile} ${nexusUrl}/${nexusRepository}/${jarFile.name}
    """
}
