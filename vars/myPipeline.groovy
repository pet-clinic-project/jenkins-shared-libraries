import org.techiescamp.GlobalConfig

def call() {
    echo "Using global variable: ${GlobalVar.versionTag}"
    echo "Using global variable: ${EndPoint.sonarEndPoint}"
    echo "Using global variable: ${EndPoint.nexusEndPoint}"
    echo "Using global variable: ${AwsConfig.awsRegion}"
    echo "Using global variable: ${AwsConfig.ecrRegistry}"
}
