def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "hadolint --ignore DL3008 --ignore DL3015 --ignore DL3047 ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Exit Code: ${hadolintOutput}"

    if (hadolintOutput != 0) {
        error "Hadolint failed with exit code ${hadolintOutput}"
    }
}
