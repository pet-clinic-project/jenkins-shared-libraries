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

def push(String imageName, String imageTag, String credentialsId) {
    try {
        withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_HUB_USR', passwordVariable: 'DOCKER_HUB_PSW')]) {
            sh """
                mkdir -p /kaniko/.docker
                echo '{"auths":{"https://index.docker.io/v1/":{"auth":"'"\$(echo -n ${DOCKER_HUB_USR}:${DOCKER_HUB_PSW} | base64)"'"}}}' > /kaniko/.docker/config.json
            """
            def dockerPush = """
                /kaniko/executor \
                --dockerfile="${WORKSPACE}/Dockerfile" \
                --context "${WORKSPACE}" \
                --destination "${imageName}:${imageTag}"
            """

            def exitCode = sh(script: dockerPush, returnStatus: true)

            if (exitCode != 0) {
                error "Kaniko push failed with exit code: ${exitCode}"
            } else {
                echo "Kaniko push succeeded."
            }
        }
    } catch (Exception e) {
        error "Exception during Kaniko push: ${e.getMessage()}"
    }
}


