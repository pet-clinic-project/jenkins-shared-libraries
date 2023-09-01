def slimImage(imageNameAndTag) {
    sh "docker-slim build --http-probe ${imageNameAndTag}"
}
