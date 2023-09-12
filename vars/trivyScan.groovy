def call(String imageNameAndTag) {
    def htmlTemplate = readFile("trivy/html.tpl")

    def command = "trivy image --format template --template '@${htmlTemplate}' -o report.html ${imageNameAndTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}
