def call(String imageNameAndTag, String htmlTemplate) {
    def htmlTemplate = htmlTemplate.replace("'", "\\'")

    def command = "trivy image --format template --template '{{${htmlTemplate}}}' -o report.html ${imageNameAndTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}
