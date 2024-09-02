def build() {
    try {
        def dockerBuild = """
            /kaniko/executor \
            --dockerfile="${WORKSPACE}/Dockerfile" \
            --context `pwd` \
            --tarPath="${WORKSPACE}/${BUILD_NUMBER}.tar" \
            --no-push
        """

        def exitCode = sh(script: dockerBuild, returnStatus: true)

        if (exitCode != 0) {
            error "Kaniko build failed with exit code: ${exitCode}"
        } else {
            echo "Kaniko build succeeded."
        }
    } catch (Exception e) {
        error "Exception during Kaniko build: ${e.getMessage()}"
    }
}

def push() {
    // Use environment variables for Docker credentials
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        // Create Docker config JSON using environment variables
        def dockerConfigJson = """
        {
            "auths": {
                "https://index.docker.io/v1/": {
                    "auth": "${DOCKER_USERNAME}:${DOCKER_PASSWORD}".bytes.encodeBase64().toString()
                }
            }
        }
        """
        
        // Write Docker config to a file in the workspace
        writeFile file: "${WORKSPACE}/docker-config.json", text: dockerConfigJson

        // Define Kaniko command using the Docker config from workspace
        def kanikoCommand = """
            /kaniko/executor --dockerfile="${WORKSPACE}/Dockerfile" \
                             --context "${WORKSPACE}" \
                             --destination "aswinvj/test:1.0.${BUILD_NUMBER}" \
                             --dockerconfig="${WORKSPACE}/docker-config.json"
        """

        // Execute the Kaniko command
        def kanikoOutput = sh(script: kanikoCommand, returnStatus: true)

        echo "Kaniko Exit Code: ${kanikoOutput}"

        if (kanikoOutput != 0) {
            error "Kaniko failed with exit code ${kanikoOutput}"
        }

        // Clean up the temporary Docker config file
        sh "rm -f ${WORKSPACE}/docker-config.json"
    }
}