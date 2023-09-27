def call(Map params) {
   def projectDirectory = params.projectDirectory
   def tfstateFile = params.tfstateFile
   def variableFile = params.variableFile

   terraformInit(projectDirectory, tfstateFile, variableFile)
}

def terraformInit(project_dir, tfstateFile, var_file) {
   dir(project_dir) {
      def terraformInitCommand = """
        terraform init \\
          -backend-config="key=dev/${tfstateFile}" \\
          -backend-config="bucket=dcube-terraform-state" \\
          -backend-config="region=us-west-2" \\
          -backend-config="dynamodb_table=terraform-state-lock" \\
          -var-file=../../vars/infra/dev/${var_file}
      """

      sh terraformInitCommand
   }
}