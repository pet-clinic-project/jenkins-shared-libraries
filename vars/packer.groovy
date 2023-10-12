def validate(Map params) {
    runPackerCommand(params, 'validate')
}

def build(Map params) {
    runPackerCommand(params, 'build')
}

def runPackerCommand(Map params, String command) {
    def packerCommand = "packer $command ${params.packerFile}"

    dir(params.projectDir) {
        sh packerCommand
    }
}
