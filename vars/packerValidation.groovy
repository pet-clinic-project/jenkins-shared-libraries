def call(String packerFilePath) {
    sh "packer validate ${packerFilePath}"
}
