def call(Map scanimage) {
    
    def dockerImage = docker.trivy("${scanimage.imageName}:${scanimage.versionTag}")

}