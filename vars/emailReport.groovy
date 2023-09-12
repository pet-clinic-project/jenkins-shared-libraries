def emailReport(reportPath, imageNameAndTag ,recipient) {
    def email = emailext(
        subject: "Trivy Scan Report",
        body: "Attached is the Trivy scan report for ${imageNameAndTag}.",
        to: "${recipient}",
        attach: "${reportPath}"
    )

  // Send the email.
    email.send()
}