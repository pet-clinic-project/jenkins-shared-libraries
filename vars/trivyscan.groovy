def call (map scanParams) {
    def fullImageName = "${scanParams.imageName}:${scanParams.imageVersion}"
    def trivyCommand = "trivy image ${fullImageName}"

    echo "Trivy Scan Results:"
    sh trivyCommand
}