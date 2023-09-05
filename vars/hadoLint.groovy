import globalVariables

def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "docker run --rm -i hadolint/hadolint:${globalVariables.hadolintVersion} < ${dockerFilePath}"
    echo "Executing Hadolint command: ${hadolintCommand}" // Add this line for debugging

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"

    if (hadolintOutput != 0) {
        error("Hadolint failed with exit code ${hadolintOutput}")
    }
}

