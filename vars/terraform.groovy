def init(String directory, String bucket, String key, String region) {
    script {
        def terraformInitCommand = """
            terraform -chdir=${directory} init \
            -backend-config="bucket=${bucket}" \
            -backend-config="key=${key}" \
            -backend-config="region=${region}"
        """

        def terraformInitOutput = sh(script: terraformInitCommand, returnStatus: true)

        echo "Terraform Init Exit Code: ${terraformInitOutput}"

        if (terraformInitOutput != 0) {
            error "Terraform init failed with exit code ${terraformInitOutput}"
        }
    }
}

def plan(String directory) {
    script {
        def terraformplanCommand = """
            terraform -chdir=${directory} plan
        """

        def terraformplanOutput = sh(script: terraformplanCommand, returnStatus: true)

        echo "Terraform plan Exit Code: ${terraformplanOutput}"

        if (terraformplanOutput != 0) {
            error "Terraform plan failed with exit code ${terraformplanOutput}"
        }
    }
}