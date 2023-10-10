def call(String packerFile, String projectDir) {
    dir(projectDir){
        sh "packer validate ${packerFile}"
    }
}
