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

def push(String credentialsId = 'docker-hub-credentials', String destination = 'aswinvj/test:1.0') {
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        
        // Generate base64 encoded auth string using Groovy
        def encodedAuth = "${DOCKER_USERNAME}:${DOCKER_PASSWORD}".bytes.encodeBase64().toString()

        // Create config.json content
        def dockerConfigJson = """
        {
            "auths": {
                "https://index.docker.io/v1/": {
                    "auth": "${encodedAuth}"
                }
            }
        }
        """
        
        // Write Docker config to the Kaniko config location within the pod
        writeFile file: '/kaniko/.docker/config.json', text: dockerConfigJson

        // Define Kaniko command to build and push the image
        def kanikoCommand = """
            /kaniko/executor --dockerfile="${WORKSPACE}/Dockerfile" \
                             --context="${WORKSPACE}" \
                             --destination=${destination}
        """
        
        try {
            // Execute the Kaniko command
            def kanikoOutput = sh(script: kanikoCommand, returnStdout: true, returnStatus: true)

            echo "Kaniko Exit Code: ${kanikoOutput.status}"
            echo "Kaniko Output: ${kanikoOutput.stdout}"

            if (kanikoOutput.status != 0) {
                error "Kaniko failed with exit code ${kanikoOutput.status}. Output: ${kanikoOutput.stdout}"
            }
        } finally {
            // Clean up the config file if needed
            sh "rm -f /kaniko/.docker/config.json"
        }
    }
}

def test() {
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USR', passwordVariable: 'DOCKER_HUB_PSW')]) {
        script {
            try {
                sh """
                    echo '{"auths":{"https://index.docker.io/v1/":{"auth":"'"\$(echo -n ${DOCKER_HUB_USR}:${DOCKER_HUB_PSW} | base64)"'"}}}' > /kaniko/.docker/config.json
                    /kaniko/executor --dockerfile="/Dockerfile" --context "." --destination "aswinvj/test:2.0"
                """
            } catch (Exception e) {
                echo "Error occurred during Kaniko build and push: ${e.getMessage()}"
                currentBuild.result = 'FAILURE'
                throw e
            }
        }
    }
}



