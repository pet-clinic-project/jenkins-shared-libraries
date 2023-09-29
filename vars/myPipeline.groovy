import org.techiescamp.GlobalConfig

def call() {
    echo "Using global variable: ${versionTag}"
    echo "Using global variable: ${sonarEndPoint}"
    echo "Using global variable: ${nexusEndPoint}"
    echo "Using global variable: ${awsRegion}"
    echo "Using global variable: ${ecrRegistry}"
}
