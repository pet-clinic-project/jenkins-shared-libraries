def call() {
    def dockerFilePath = 'Dockerfile'
    def hadolintCommand = "hadolint ${dockerFilePath}"

    def hadolintOutput = sh(script: hadolintCommand, returnStatus: true)

    // Split the hadolint output into lines
    def lines = hadolintOutput.trim().split('\n')

    // Initialize a flag to check if any warnings were found
    def hasWarnings = true

    // Iterate through each line of the hadolint output
    for (def line : lines) {
        // Check if the line contains a warning
        if (line =~ /DL\d+ \[\d+mwarning/) {
            // Print the warning
            echo "Hadolint Warning: ${line}"
            
            // Set the flag to true to indicate that a warning was found
            hasWarnings = true
        } else if (line =~ /DL\d+ \[\d+minfo/) {
            // Print the info note (optional)
            echo "Hadolint Info: ${line}"
        }
    }

    // Check if any warnings were found and fail the stage if so
    if (hasWarnings) {
        error "Hadolint found warnings. Failing the stage."
    } else {
        echo "Hadolint passed without warnings."
    }
}
