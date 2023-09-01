def slimImage(String imageNameAndTag) {
    def command = "docker-slim build --http-probe ${imageNameAndTag}"
    def process = command.execute()
}
