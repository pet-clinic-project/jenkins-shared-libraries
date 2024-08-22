def call(String recipient) {
    script {
        def tplContent = libraryResource "notification/notify.tpl"

        def buildStatus = currentBuild.currentResult
        def statusColor = buildStatus == 'SUCCESS' ? 'green' : 'red'

        tplContent = tplContent.replace('${BUILD_STATUS}', buildStatus)
                               .replace('${STATUS_COLOR}', statusColor)

        writeFile file: "${WORKSPACE}/notify.tpl", text: tplContent
    }

    def email = emailext(
        subject: "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.currentResult}",
        body: readFile("${WORKSPACE}/notify.tpl"),
        to: recipient,
        mimeType: 'text/html'
    )
}
