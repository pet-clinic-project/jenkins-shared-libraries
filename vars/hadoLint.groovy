def call() {
    def hadolintConfigFile = libraryResource('hadolint/.hadolint.yaml')

    def dockerFilePath = "${WORKSPACE}/Dockerfile"
    def hadolintCommand = "hadolint --config ${hadolintConfigFile} ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Exit Code: ${hadolintOutput}"

    if (hadolintOutput != 0) {
        error "Hadolint failed with exit code ${hadolintOutput}"
    }
}
