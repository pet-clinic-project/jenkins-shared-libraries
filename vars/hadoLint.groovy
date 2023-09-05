def call() {
    def dockerFilePath = 'Dockerfile'
    def cmd = "docker run --rm -i hadolint/hadolint:v2.7.0 < ${dockerFilePath}"

}
