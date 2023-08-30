def call(Map trivyScanParams) {

    def trivyScan = trivy.image("${trivyScanParams.imageName}:${trivyScanParams.versionTag}")

    echo trivyScan

}