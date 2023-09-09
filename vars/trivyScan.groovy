def call(String imageNameAndTag) {
    def templateContent = libraryResource('trivy/html.tpl')
    def command = "trivy image --format template --template '@html.tpl' -o report.html ${imageNameAndTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}

def libraryResource(String resourcePath) {
    return libraryResourceLoader.fromClassLoader(getClass().classLoader).loadResource(resourcePath).text
}
