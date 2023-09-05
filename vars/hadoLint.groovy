// vars/hadolint.groovy

def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintVersion = library.globalVariables.hadolintVersion
    def hadolintCommand = "docker run --rm -i hadolint/hadolint:${hadolintVersion} < ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"
}
