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
    } else {
        // Retrieve scan report using SonarCloud API
        def apiUrl = "${GlobalConfig.sonarEndPoint}/api/measures/component"
        def analysisId = sh(script: 'mvn sonar:sonar -Dsonar.showProfiling=true | grep "ANALYSIS SUCCESSFUL, you can browse " | cut -d\' \' -f10', returnStdout: true).trim()

        def apiParams = [
            component: projectKey,
            metricKeys: 'code_smells,bugs,vulnerabilities,coverage,ncloc,violations',
            additionalParams: [
                'metricKeys': 'code_smells,bugs,vulnerabilities,coverage,ncloc,violations'
            ]
        ]

        def apiResponse = httpRequest(
            acceptType: 'APPLICATION_JSON',
            contentType: 'APPLICATION_JSON',
            httpMode: 'GET',
            timeout: 30,
            url: apiUrl,
            authentication: "${sonarToken}",
            customHeaders: [[name: 'Authorization', value: "Bearer ${sonarToken}"]],
            ignoreSslErrors: true,
            validResponseCodes: '200',
            responseHandle: 'NONE',
            requestBody: groovy.json.JsonOutput.toJson(apiParams)
        )

        echo "SonarQube scan report: ${apiResponse}"
    }
}
