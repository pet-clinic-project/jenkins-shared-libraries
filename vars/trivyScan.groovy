def setupTrivyFiles() {
    script {
        def tplContent = libraryResource "trivy/html.tpl"
        writeFile file: "${WORKSPACE}/html.tpl", text: tplContent
        echo "HTML Template written to ${WORKSPACE}/html.tpl"

        def trivyConfigContent = libraryResource "trivy/trivy.yml"
        writeFile file: "${WORKSPACE}/trivy.yml", text: trivyConfigContent
        echo "Trivy config written to ${WORKSPACE}/trivy.yml"
    }
}

def runTrivyCommand(String command) {
    def exitCode = sh(script: command, returnStatus: true)
    def output = sh(script: command, returnStdout: true).trim()

    if (exitCode != 0) {
        echo "Trivy scan encountered issues. Exit code: ${exitCode}. Review the generated report."
    } else {
        echo "Trivy scan completed successfully with no critical vulnerabilities."
    }

    echo "Trivy Scan Results:"
    echo output

    return [exitCode: exitCode, output: output]
}

def kaniko() {
    try {
        setupTrivyFiles()

        def command = "trivy image --config ${WORKSPACE}/trivy.yml --format template --template '@${WORKSPACE}/html.tpl' -o ${WORKSPACE}/trivy-report.html --input ${WORKSPACE}/${BUILD_NUMBER}.tar"
        def result = runTrivyCommand(command)

        return result.exitCode
    } catch (Exception e) {
        error "Exception during Trivy scan for Kaniko image: ${e.getMessage()}"
    }
}

def docker(String imageName, String imageTag) {
    try {
        setupTrivyFiles()

        def command = "trivy image --config ${WORKSPACE}/trivy.yml --template '@${WORKSPACE}/html.tpl' -o ${WORKSPACE}/trivy-report.html ${imageName}:${imageTag}"
        def result = runTrivyCommand(command)

        return result.exitCode
    } catch (Exception e) {
        error "Exception during Trivy scan for Docker image ${imageName}:${imageTag}: ${e.getMessage()}"
    }
}