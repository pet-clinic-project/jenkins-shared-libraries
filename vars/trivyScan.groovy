def call(String imageNameAndTag) {
    script {
                    def tplContent = libraryResource "trivy/html.tpl"
                    writeFile file: "${WORKSPACE}/html.tpl", text: tplContent
                }

    def command = "trivy image --exit-code 1 --severity CRITICAL --cache-dir /tmp/trivy-cache --format template --template '@${WORKSPACE}/html.tpl' -o ${WORKSPACE}/trivy-report.html ${imageNameAndTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}