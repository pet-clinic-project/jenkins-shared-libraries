def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}"
    def dockerfileExists = fileExists(dockerfilePath)

    if (!dockerfileExists) {
        error "Dockerfile not found at path: ${dockerfilePath}"
        return
    }

    def proc = hadolintCommand.execute()
    proc.waitForOrKill(30000)

    if (proc.exitValue() == 0) {
        echo "Dockerfile passed Hadolint checks."
    } else {
        error "Hadolint found issues in the Dockerfile."
    }
}
