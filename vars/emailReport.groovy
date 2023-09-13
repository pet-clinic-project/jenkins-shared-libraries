def call(String reportPath, String imageNameAndTag ,String recipient) {
    def trivyNotification = libraryResource 'notification/tivyNotify.tpl'
    def email = emailext(
        subject: "${PROJECT_NAME} - ${BUILD_NUMBER}",
        body:  "${trivyNotification}",
        to: "${recipient}",
    )
}