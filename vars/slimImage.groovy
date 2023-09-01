def slimImage(String imageNameAndTag, String prompt) {
    def command = "docker-slim build --http-probe ${imageNameAndTag}"
    def process = command.execute()

    println(prompt)
    System.console()?.readLine()

}
