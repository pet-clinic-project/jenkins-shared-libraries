def call(String nexusRepository, String nexusCredentialsId, String jarFileName) {
    
    withCredentials([usernamePassword(credentialsId: nexusCredentialsId, passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
    
        def jarFile = findFiles(glob: 'target/*.jar').first()

        def uploadCommand = "curl -v -u ${USERNAME}:${PASSWORD} --upload-file ${jarFile} ${GlobalConfig.nexusEndPoint}/${nexusRepository}/${jarFile.name}"
        def exitCode = sh(script: uploadCommand, returnStatus: true)

        if (exitCode != 0) {
            error "Failed to upload JAR file to Nexus. Exit code: ${exitCode}"
        }
    }
}
