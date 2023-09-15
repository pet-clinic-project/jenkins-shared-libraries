def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"


}
