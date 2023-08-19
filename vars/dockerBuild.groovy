def call(Map buildParams) {

    dockerImage = docker.build(buildParams.imageName:buildParams.versionTag)
}