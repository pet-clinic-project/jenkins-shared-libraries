def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}" --fail_on_error
    def dockerfileExists = fileExists(dockerfilePath)

}
