def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}"
    def cmd = "docker run --rm -i hadolint/hadolint:v2.7.0 < ${dockerfilePath}"

}
