def validate(String packerFile, String projectDir) {
    dir(projectDir){
        sh "packer validate ${packerFile}"
    }
}

def build(String packerFile, String projectDir) {
    dir(projectDir){
        sh "packer build ${packerFile}"
    }
}
