def call(String imageNameAndTag) {
    def command = "sudo trivy image ${imageNameAndTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}