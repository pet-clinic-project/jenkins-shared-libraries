def call(String recipient) {
    def email = emailext(
        subject: "${JOB_NAME} - ${BUILD_NUMBER}",
        body:  """<html><body>
                    <p>Click <a href="${BUILD_URL}">here</a> to view the build details.</p>
                </body></html>""",
        to: "${recipient}",
    )
}