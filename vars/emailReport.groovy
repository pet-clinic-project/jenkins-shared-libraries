def call(String reportPath, String imageNameAndTag ,String recipient) {
    def email = emailext(
        subject: "BUILD - ${BUILD_NUMBER}",
        body:  "URL - ${BUILD_URL}",,
        to: "${recipient}",
        attachmentsPattern: "${reportPath}"
    )
}