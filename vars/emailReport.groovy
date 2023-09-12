def call(String reportPath, String imageNameAndTag ,String recipient) {
    def email = emailPublisher(
        subject: "Trivy Scan Report",
        body: "Attached is the Trivy scan report for ${imageNameAndTag}.",
        to: "${recipient}",
        attachments: "${reportPath}"
    )

  // Send the email.
    email.send()
}