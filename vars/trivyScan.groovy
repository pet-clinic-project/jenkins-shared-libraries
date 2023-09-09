def call(String imageNameAndTag) {
    def resourcePath = "${JENKINS_HOME}/workspace/${JOB_NAME}@libs/resources/trivy/html.tpl"
    def templateContent = readFileFromWorkspace(resourcePath)

    def command = "trivy image --format template --template '@html.tpl' -o report.html ${imageNameAndTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}