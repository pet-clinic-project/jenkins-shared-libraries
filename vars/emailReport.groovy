def call(String recipient) {
    script {
        def tplContent = libraryResource "notification/notify.tpl"
        writeFile file: "${WORKSPACE}/notify.tpl", text: tplContent
    }

    def email = emailext(
        subject: "${JOB_NAME} - Build #${BUILD_NUMBER} - ${currentBuild.result}",
        body: readFile("${WORKSPACE}/notify.tpl"),
        to: "${recipient}",
        mimeType: 'text/html'
    )
}
