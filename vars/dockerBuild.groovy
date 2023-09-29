import org.techiescamp.GlobalConfig

def call(Map buildParams) {
    
    def dockerImage = docker.build("${buildParams.imageName}:${GlobalConfig.versionTag}.${BUILD_NUMBER}")

}
