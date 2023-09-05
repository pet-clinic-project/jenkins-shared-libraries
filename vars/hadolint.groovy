def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerFilePath}"
    def cmd = "docker run --rm -i hadolint/hadolint:v2.7.0 < ${dockerFilePath}"

}
