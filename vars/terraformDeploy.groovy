import org.techiescamp.GlobalConfig

def init(Map params) {
   def projectDirectory = params.projectDirectory
   def tfstateFile = params.tfstateFile
   def variableFile = params.variableFile

   dir(projectDirectory) {
      def terraformInitCommand = """
        terraform init \\
          -backend-config="key=dev/${tfstateFile}" \\
          -backend-config="bucket=${GlobalConfig.backendS3Bucket}" \\
          -backend-config="region=${GlobalConfig.awsRegion}" \\
          -backend-config="dynamodb_table=${GlobalConfig.backendDynamodbTable}" \\
          -var-file=../../vars/infra/stage/${variableFile}
      """

      sh terraformInitCommand
   }
}

def validate(Map params) {
   def projectDirectory = params.projectDirectory

   dir(projectDirectory) {
      def terraformValidateCommand = "terraform validate"
      sh terraformValidateCommand 
   }   
}
