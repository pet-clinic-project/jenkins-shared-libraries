def call(String reportPath, String imageNameAndTag ,String recipient) {
    def email = emailext(
        subject: "BUILD - ${BUILD_NUMBER}",
        body:  """<html><body>
                    <p>Click <a href="${buildUrl}">here</a> to view the build details.</p>
                    <p>Attached is the Trivy scan report for ${imageNameAndTag}.</p>
                </body></html>""",
        to: "${recipient}",
        attachmentsPattern: "${reportPath}"
    )
}