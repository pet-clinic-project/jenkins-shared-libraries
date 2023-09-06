def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "docker run --rm -i hadolint/hadolint < ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"


}
