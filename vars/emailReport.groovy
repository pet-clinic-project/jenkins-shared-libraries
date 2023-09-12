def call(String reportPath, String imageNameAndTag ,String recipient) {
    def email = emailext(
        subject:  "${BUILD_NUMBER} - ${BUILD_URL}",
        body: "Attached is the Trivy scan report for ${imageNameAndTag}.",
        to: "${recipient}",
        attachmentsPattern: "${reportPath}"
    )
}