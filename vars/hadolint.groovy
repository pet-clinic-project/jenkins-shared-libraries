def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}"
    def dockerfileExists = fileExists(dockerfilePath)
}
