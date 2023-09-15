def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerFilePath}"

    // Run the hadolint command and capture both exit status and output
    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    // Check if there are any warning messages in the hadolint output
    def hasWarnings = hadolintOutput.contains("warning")

    // Print the hadolint output for reference
    echo "Hadolint Output:"
    echo hadolintOutput

    // Determine whether to fail the stage based on the presence of warnings
    if (hasWarnings) {
        error("Hadolint detected warnings. Failing the stage.")
    } else {
        echo "No warnings detected. Continuing with the stage."
    }
}
