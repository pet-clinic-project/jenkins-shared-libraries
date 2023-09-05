@Library('jenkins-shared-library@develop')

import globalVariables.groovy

def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "docker run --rm -i hadolint/hadolint:${globalVariables.hadolintVersion} < ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    echo "Hadolint Output: ${hadolintOutput}"


}
