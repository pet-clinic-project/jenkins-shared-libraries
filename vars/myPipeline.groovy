import org.techiescamp.GlobalConfig

def call() {
    echo "Using global variable: ${GlobalConfig.versionTag}"
    echo "Using global variable: ${EndPoint.sonarEndpoint}"
    echo "Using global variable: ${EndPoint.nexusEndpoint}"
    echo "Using global variable: ${AwsConfig.awsRegion}"
    echo "Using global variable: ${AwsConfig.ecrRegistry}"
}
