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
        // Generate base64 encoded auth string
        def encodedAuth = sh(script: "echo -n ${DOCKER_USERNAME}:${DOCKER_PASSWORD} | base64", returnStdout: true).trim()

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
        
        // Write Docker config to the standard Kaniko config location
        writeFile file: '/kaniko/.docker/config.json', text: dockerConfigJson

        // Define Kaniko command
        def kanikoCommand = """
            /kaniko/executor --dockerfile="${WORKSPACE}/Dockerfile" \
                             --context "${WORKSPACE}" \
                             --destination "${DOCKER_USERNAME}/test:1.0.${BUILD_NUMBER}"
        """

        // Execute the Kaniko command
        def kanikoOutput = sh(script: kanikoCommand, returnStatus: true)

        echo "Kaniko Exit Code: ${kanikoOutput}"

        if (kanikoOutput != 0) {
            error "Kaniko failed with exit code ${kanikoOutput}"
        }
    }
}