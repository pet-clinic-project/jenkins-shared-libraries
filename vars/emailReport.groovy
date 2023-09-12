def emailReport(reportPath) {
  // Get the email address of the recipient.
    def recipient = "aswin@crunchops.com"

  // Create a new email message.
    def email = emailext(
        subject: "Trivy Scan Report",
        body: "Attached is the Trivy scan report for ${imageNameAndTag}.",
        attach: reportPath
    )

  // Send the email.
    email.send()
}