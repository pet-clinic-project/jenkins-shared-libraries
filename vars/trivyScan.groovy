def call(Map buildParams) {
    
    def dockerImage = trivy.image("${buildParams.imageName}:${buildParams.versionTag}")

}
