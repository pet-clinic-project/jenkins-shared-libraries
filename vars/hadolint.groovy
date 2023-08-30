def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint --ignore DL3008 --ignore DL3015 --ignore DL3047 ${dockerfilePath}"
    def dockerfileExists = fileExists(dockerfilePath)

}
