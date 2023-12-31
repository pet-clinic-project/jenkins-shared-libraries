import org.techiescamp.GlobalConfig

def call(String projectKey, String organization, String sonarTokenCredentialId) {
    def sonarToken = withCredentials([string(credentialsId: sonarTokenCredentialId, variable: 'SONAR_TOKEN')]) {
        return SONAR_TOKEN
    }

    def sonarCommand = """
        mvn sonar:sonar \\
            -Dsonar.projectKey=${projectKey} \\
            -Dsonar.organization=${organization} \\
            -Dsonar.host.url=${GlobalConfig.sonarEndPoint} \\
            -Dsonar.login=${sonarToken}
    """

    def status = sh(script: sonarCommand, returnStatus: true)
    
    if (status != 0) {
        error "SonarQube scan failed with exit code: ${status}"
    }
}