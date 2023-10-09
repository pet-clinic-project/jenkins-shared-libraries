def call(String packerFilePath) {
    sh "packer build ${packerFilePath}"
}
