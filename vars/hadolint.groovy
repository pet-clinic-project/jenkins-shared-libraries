def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}"
    def dockerfileExists = fileExists(dockerfilePath)

    echo "Hadolint output:"
    echo proc.text
}
