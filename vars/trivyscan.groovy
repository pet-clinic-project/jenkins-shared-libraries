def call(Map scanParams) {
    def fullImageName = "${scanParams.imageName}:${scanParams.imageVersion}"
    def dockerImage = "trivy image ${fullImageName}"

    echo "Running command: ${dockerImage}"

}