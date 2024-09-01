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

def push(String imageName, String imageTag) {
    try {
        def dockerPush = """
            /kaniko/executor \
            --dockerfile="${WORKSPACE}/Dockerfile" \
            --context `pwd` \
            --destination "${imageName}:${imageTag}"
        """

        def exitCode = sh(script: dockerPush, returnStatus: true)

        if (exitCode != 0) {
            error "Kaniko push failed with exit code: ${exitCode}"
        } else {
            echo "Kaniko push succeeded."
        }
    } catch (Exception e) {
        error "Exception during Kaniko push: ${e.getMessage()}"
    }
}
