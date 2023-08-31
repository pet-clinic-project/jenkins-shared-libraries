def slimBuild(Map imageInfo) {
    def command = "docker-slim build --http-probe=false ${imageInfo.imageName}:${imageInfo.tag}"
    def process = command.execute()
}