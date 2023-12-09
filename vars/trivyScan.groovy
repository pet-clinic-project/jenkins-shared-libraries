import org.techiescamp.GlobalConfig

def call(String imageName) {
    script {
                    def tplContent = libraryResource "trivy/html.tpl"
                    writeFile file: "${WORKSPACE}/html.tpl", text: tplContent

                    def trivyConfigContent = libraryResource "trivy/trivy.yml"
                    writeFile file: "${WORKSPACE}/trivy.yml", text: trivyConfigContent
                }

    def command = "trivy image --config ${WORKSPACE}/trivy.yml -o ${WORKSPACE}/trivy-report.html ${imageName}:${GlobalConfig.versionTag}.${BUILD_NUMBER}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}