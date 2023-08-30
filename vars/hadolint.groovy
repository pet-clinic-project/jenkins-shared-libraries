def call() {
    def dockerfilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerfilePath}"
    def cmd = "docker run --rm -i hadolint/hadolint:v2.7.0 < ${dockerfilePath}"
    
    def proc = cmd.execute()
    proc.waitForOrKill(10000)
    
    def exitValue = proc.exitValue()
    
    if (exitValue == 0) {
        echo "Dockerfile passed hadolint checks."
    } else {
        error "Dockerfile failed hadolint checks."
    }

}
