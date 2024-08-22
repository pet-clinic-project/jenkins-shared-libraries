def call(String recipient) {
    script {
        def tplContent = libraryResource "notification/notify.tpl"
        writeFile file: "${WORKSPACE}/notify.tpl", text: tplContent
    }

        def email = emailext(
            subject: "${JOB_NAME} - ${BUILD_NUMBER}",
            body: readFile("${WORKSPACE}/notify.tpl"),
            to: "${recipient}",
            mimeType: 'text/html'
        )
}
