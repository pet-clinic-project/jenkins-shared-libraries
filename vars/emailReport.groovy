def emailReport(reportPath, imageNameAndTag) {
  // Get the email address of the recipient.
    def recipient = "aswin@crunchops.com"
    def reportPath = "/home/ubuntu/trivy-report.html"
  // Create a new email message.
    def email = emailext(
        subject: "Trivy Scan Report",
        body: "Attached is the Trivy scan report for ${imageNameAndTag}.",
        to: recipient,
        attach: reportPath
    )

  // Send the email.
    email.send()
}