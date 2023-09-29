import org.techiescamp.GlobalConfig

def call() {
    echo "Using global variable: ${GlobalConfig.versionTag}"
    echo "Using global variable: ${GlobalConfig.sonarEndPoint}"
    echo "Using global variable: ${GlobalConfig.nexusEndPoint}"
    echo "Using global variable: ${GlobalConfig.SubConfig.awsRegion}"
    echo "Using global variable: ${GlobalConfig.SubConfig.ecrRegistry}"
}
