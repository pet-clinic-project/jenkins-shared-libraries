def call(String reportPath, String recipient) {
    if (fileExists(reportPath)) {
        echo "File found: ${reportPath}"

        emailext(
            subject: "${JOB_NAME} - ${BUILD_NUMBER}",
            body: """<html><body>
                        <p>Click <a href="${BUILD_URL}">here</a> to view the build details.</p>
                        <p>The scan report is attached to this email.</p>
                    </body></html>""",
            to: "${recipient}",
            mimeType: 'text/html',
            attachmentsPattern: reportPath
        )
    } else {
        echo "File not found: ${reportPath}"
        error "Report file not found. Cannot send email with attachment."
    }
}
