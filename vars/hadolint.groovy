def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}"
    def dockerfileExists = fileExists(dockerfilePath)
    
    def hadolintOutput = hadolintCommand.execute().text

    println("Hadolint Output:")
    println(hadolintOutput)

    println("Dockerfile Exists: ${dockerfileExists}")
}
