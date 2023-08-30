def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}"
    def dockerfileExists = fileExists(dockerfilePath)

    if (dockerfileExists) {
        def lintProcess = hadolintCommand.execute()
        lintProcess.waitFor()

        if (lintProcess.exitValue() != 0) {
            throw new RuntimeException("Lint errors found in the Dockerfile")
        } else {
            println("No lint errors found.")
        }
    } else {
        throw new RuntimeException("Dockerfile not found.")
    }
}
