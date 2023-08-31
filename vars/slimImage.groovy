def slimBuild(Map imageInfo) {
    def command = "docker-slim build --http-probe=false ${imageInfo.imageName}:${imageInfo.tag}"
    def process = command.execute()

    System.console()?.readLine("Press Enter to continue...")
}