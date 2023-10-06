import org.techiescamp.GlobalConfig

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
          -backend-config="bucket=${GlobalConfig.backendS3Bucket}" \\
          -backend-config="region=${GlobalConfig.awsRegion}" \\
          -backend-config="dynamodb_table=${GlobalConfig.backendDynamodbTable}" \\
          -var-file=../../vars/infra/stage/${var_file}
      """

      sh terraformInitCommand
   }
}
