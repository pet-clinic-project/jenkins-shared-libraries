def call(String imageNameAndTag) {
    def htmlTemplate = libraryResource("trivy/html.tpl")

    def templateFile = file("~/workspace/${JOB_NAME}/temp_template.html")
    templateFile.text = htmlTemplate


    def command = "trivy image --format template --template '@${templateFile}' -o report.html ${imageNameAndTag}"
    def trivyOutput = sh(script: command, returnStdout: true).trim()

    echo "Trivy Scan Results:"
    echo trivyOutput
}
