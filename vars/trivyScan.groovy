def kaniko() {
    try {
        script {
            def tplContent = libraryResource "trivy/html.tpl"
            writeFile file: "${WORKSPACE}/html.tpl", text: tplContent
            echo "HTML Template written to ${WORKSPACE}/html.tpl"

            def trivyConfigContent = libraryResource "trivy/trivy.yml"
            writeFile file: "${WORKSPACE}/trivy.yml", text: trivyConfigContent
            echo "Trivy config written to ${WORKSPACE}/trivy.yml"
        }

        def command = "trivy image --config ${WORKSPACE}/trivy.yml --template '@${WORKSPACE}/html.tpl' -o ${WORKSPACE}/trivy-report.html --input ${WORKSPACE}/${BUILD_NUMBER}.tar"
        def trivyOutput = sh(script: command, returnStatus: true, returnStdout: true).trim()

        if (trivyOutput != 0) {
            echo "Trivy scan encountered issues. Exit code: ${trivyOutput}. Review the generated report."
        } else {
            echo "Trivy scan completed successfully with no critical vulnerabilities."
        }

        echo "Trivy Scan Results:"
        echo trivyOutput

    } catch (Exception e) {
        error "Exception during Trivy scan for Kaniko image: ${e.getMessage()}"
    }
}

def docker(String imageName, String imageTag) {
    try {
        script {
            def tplContent = libraryResource "trivy/html.tpl"
            writeFile file: "${WORKSPACE}/html.tpl", text: tplContent
            echo "HTML Template written to ${WORKSPACE}/html.tpl"

            def trivyConfigContent = libraryResource "trivy/trivy.yml"
            writeFile file: "${WORKSPACE}/trivy.yml", text: trivyConfigContent
            echo "Trivy config written to ${WORKSPACE}/trivy.yml"
        }

        def command = "trivy image --config ${WORKSPACE}/trivy.yml --template '@${WORKSPACE}/html.tpl' -o ${WORKSPACE}/trivy-report.html ${imageName}:${imageTag}"
        def trivyOutput = sh(script: command, returnStatus: true, returnStdout: true).trim()

        if (trivyOutput != 0) {
            echo "Trivy scan encountered issues. Exit code: ${trivyOutput}. Review the generated report."
        } else {
            echo "Trivy scan completed successfully with no critical vulnerabilities."
        }

        echo "Trivy Scan Results:"
        echo trivyOutput

    } catch (Exception e) {
        error "Exception during Trivy scan for Docker image ${imageName}:${imageTag}: ${e.getMessage()}"
    }
}
