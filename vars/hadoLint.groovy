def call() {
    def versionScript = load 'globalVariables.groovy'
    def version = versionScript.getVersion()


    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "docker run --rm -i hadolint/hadolint:${version} < ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"


}
