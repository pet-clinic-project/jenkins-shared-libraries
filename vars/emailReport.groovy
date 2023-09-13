def call(String reportPath, String imageNameAndTag ,String recipient) {
    def email = emailext(
        subject: "${PROJECT_NAME} - ${BUILD_NUMBER}",
        body:  """<html><body>
                    <p>Click <a href="${BUILD_URL}">here</a> to view the build details.</p>
                    <table border="1">
                        <tr>
                            <th>Build Number</th>
                            <td>${BUILD_NUMBER}</td>
                        </tr>
                        <tr>
                            <th>Build URL</th>
                            <td><a href="${BUILD_URL}">${BUILD_URL}</a></td>
                        </tr>
                    </table>
                    <pre>
                    ${readFile(reportPath)}
                    </pre>
                </body></html>""",
        to: "${recipient}",
    )
}