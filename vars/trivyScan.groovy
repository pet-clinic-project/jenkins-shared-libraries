def kaniko() {
    script {
                    def tplContent = libraryResource "trivy/html.tpl"
                    writeFile file: "${WORKSPACE}/html.tpl", text: tplContent

                    def trivyConfigContent = libraryResource "trivy/trivy.yml"
                    writeFile file: "${WORKSPACE}/trivy.yml", text: trivyConfigContent
                }

    def command = "trivy image --config ${WORKSPACE}/trivy.yml --template '@${WORKSPACE}/html.tpl' -o ${WORKSPACE}/trivy-report.html --input ${WORKSPACE}/${BUILD_NUMBER}.tar"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}

def docker(String imageName, String imageTag) {
    script {
                    def tplContent = libraryResource "trivy/html.tpl"
                    writeFile file: "${WORKSPACE}/html.tpl", text: tplContent

                    def trivyConfigContent = libraryResource "trivy/trivy.yml"
                    writeFile file: "${WORKSPACE}/trivy.yml", text: trivyConfigContent
                }

    def command = "trivy image --config ${WORKSPACE}/trivy.yml --template '@${WORKSPACE}/html.tpl' -o ${WORKSPACE}/trivy-report.html ${imageName}:${imageTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}