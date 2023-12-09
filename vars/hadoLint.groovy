def call() {

    def dockerFilePath = "${WORKSPACE}/Dockerfile"
    
    script {
                    def tplContent = libraryResource "hadolint/.hadolint.yaml"
                    writeFile file: "${WORKSPACE}/.hadolint.yaml", text: tplContent
                }

    def hadolintCommand = "hadolint --config ${WORKSPACE}/.hadolint.yaml ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Exit Code: ${hadolintOutput}"

    if (hadolintOutput != 0) {
        error "Hadolint failed with exit code ${hadolintOutput}"
    }
}
