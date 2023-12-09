def call() {

    def dockerFilePath = "${WORKSPACE}/Dockerfile"
    
    script {
                    def tplContent = libraryResource "hadolint/.hadolint.yaml"
                    writeFile file: "${WORKSPACE}/.hadolint.yaml", text: tplContent
                }

    def hadolintCommand = "hadolint --config ${WORKSPACE}/.hadolint.yaml ${dockerFilePath}"
}
