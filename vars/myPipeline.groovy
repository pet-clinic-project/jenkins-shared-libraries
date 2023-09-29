import org.techiescamp.GlobalConfig
import org.techiescamp.EndPoint
import org.techiescamp.AwsConfig

def call() {
    echo "Using global variable: ${GlobalConfig.versionTag}"
    echo "Using global variable: ${EndPoint.sonarEndpoint}"
    echo "Using global variable: ${EndPoint.nexusEndpoint}"
    echo "Using global variable: ${AwsConfig.awsRegion}"
    echo "Using global variable: ${AwsConfig.ecrRegistry}"
}
