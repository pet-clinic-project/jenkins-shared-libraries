def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "hadolint --ignore DL3008 --ignore DL3015 --ignore DL3047 ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"

    if (hadolintExitStatus == 0) {
        echo "Hadolint successfully passed."
    } else {
        echo "Hadolint returned a non-zero exit status: ${hadolintExitStatus}"
        // You can add additional actions or error handling here if needed
    }


}
