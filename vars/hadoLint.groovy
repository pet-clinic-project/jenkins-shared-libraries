def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"

    if (hadolintExitStatus == 0) {
        echo "Hadolint successfully passed."
    } else {
        echo "Hadolint returned a non-zero exit status: ${hadolintExitStatus}"
        // You can add additional actions or error handling here if needed
    }


}
