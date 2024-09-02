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

    // Save Docker Hub credentials to a temporary file
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USR', passwordVariable: 'DOCKER_HUB_PSW')]) {
        script {
            def dockerConfigJson = """
            {
                "auths": {
                    "https://index.docker.io/v1/": {
                        "auth": "${DOCKER_HUB_USR}:${DOCKER_HUB_PSW}".bytes.encodeBase64().toString()
                    }
                }
            }
            """
            writeFile file: "/kaniko/.docker/config.json", text: dockerConfigJson
        }
    }

    // Define Kaniko command using the temporary Docker config file
    def kanikoCommand = """
        /kaniko/executor --dockerfile="${WORKSPACE}/Dockerfile" \
                         --context 'pwd' \
                         --destination "aswinvj/test:1.0.${BUILD_NUMBER}"
    """

    // Execute the Kaniko command
    def kanikoOutput = sh(script: kanikoCommand, returnStatus: true)

    echo "Kaniko Exit Code: ${kanikoOutput}"

    if (kanikoOutput != 0) {
        error "Kaniko failed with exit code ${kanikoOutput}"
    }

    // Cleanup the temporary Docker config file
    sh "rm -f ${WORKSPACE}/docker-config.json"
}







