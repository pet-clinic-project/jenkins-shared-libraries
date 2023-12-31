import org.techiescamp.GlobalConfig

def call(String nexusRepository, String nexusCredentialsId, String jarFileName) {
    
    def nexus_cred = withCredentials([usernamePassword(credentialsId: nexusCredentialsId, passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
    
    def jarFile = "${WORKSPACE}/target/*.jar"

    def uploadCommand = "curl -v -u ${USERNAME}:${PASSWORD} --upload-file ${jarFile} ${GlobalConfig.nexusEndPoint}/${nexusRepository}/${jarFileName}"
    def exitCode = sh(script: uploadCommand, returnStatus: true)

    if (exitCode != 0) {
        error "Failed to upload JAR file to Nexus. Exit code: ${exitCode}"
        }
    }
}
