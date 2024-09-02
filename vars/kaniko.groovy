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
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        // Create Docker config JSON securely
        def auth = sh(script: "echo -n ${DOCKER_USERNAME}:${DOCKER_PASSWORD} | base64", returnStdout: true).trim()
        def dockerConfigJson = """
        {
            "auths": {
                "https://index.docker.io/v1/": {
                    "auth": "${auth}"
                }
            }
        }
        """
        
        // Write Docker config to the standard Kaniko config location
        writeFile file: '/kaniko/.docker/config.json', text: dockerConfigJson

        // Define Kaniko command
        def destination = sh(script: "echo ${DOCKER_USERNAME}/test:1.0.${BUILD_NUMBER}", returnStdout: true).trim()
        
        // Execute the Kaniko command
        def kanikoOutput = sh(script: """
            /kaniko/executor --dockerfile="${WORKSPACE}/Dockerfile" \
                             --context "${WORKSPACE}" \
                             --destination "${destination}"
        """, returnStatus: true)

        echo "Kaniko Exit Code: ${kanikoOutput}"

        if (kanikoOutput != 0) {
            error "Kaniko failed with exit code ${kanikoOutput}"
        }
    }
}