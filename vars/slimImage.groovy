def slimBuild(Map imageInfo) {
    def command = "docker-slim build --http-probe ${imageInfo.imageName}:${imageInfo.tag}"
    def process = command.execute()
}