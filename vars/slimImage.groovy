def slimImage(String imageNameAndTag) {
    def command = "docker-slim build ${imageNameAndTag}"
    def process = command.execute()

}
