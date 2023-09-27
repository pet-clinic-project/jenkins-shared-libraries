def call(String projectKey, String organization, String hostUrl, String sonarTokenCredentialId) {
    def sonarToken = withCredentials([string(credentialsId: sonarTokenCredentialId, variable: 'SONAR_TOKEN')]) {
        return env.SONAR_TOKEN
    }

    def sonarCommand = """
        mvn sonar:sonar \\
            -Dsonar.projectKey=${projectKey} \\
            -Dsonar.organization=${organization} \\
            -Dsonar.host.url=${hostUrl} \\
            -Dsonar.login=${sonarToken}
    """

    def status = sh(script: sonarCommand, returnStatus: true)
    
    if (status != 0) {
        error "SonarQube scan failed with exit code: ${status}"
    }
}