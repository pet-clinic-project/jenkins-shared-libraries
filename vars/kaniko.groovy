def build() {
    
    def dockerBuild = /kaniko/executor --dockerfile="${WORKSPACE}/Dockerfile" --context `pwd` --tarPath="${WORKSPACE}/${BUILD_NUMBER}.tar" --no-push

}

def push(String imageName, String imageTag) {
    
    def dockerPush = /kaniko/executor --dockerfile="${WORKSPACE}/Dockerfile" --context `pwd` --destination "${imageName}:${imageTag}"

}