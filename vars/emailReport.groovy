def call(String reportPath, String imageNameAndTag ,String recipient) {
    def email = emailext(
        subject: "Trivy Scan Report",
        body: "Attached is the Trivy scan report for ${imageNameAndTag}.",
        to: "${recipient}",
        attachmentsPath: "${reportPath}"
    )

    email.send()
}