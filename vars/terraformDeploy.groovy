import org.techiescamp.GlobalConfig

def call(Map params) {
   def projectDirectory = params.projectDirectory
   def variableFile = params.variableFile
   def tfstateFile = params.tfstateFile
   def planFile = params.planFile
   def redirectPlanFile = params.redirectPlanFile
   def amiId = params.amiId

   terraformInit(projectDirectory, tfstateFile, variableFile)
   terraformValidate(projectDirectory)
   terraformPlan(projectDirectory, variableFile, planFile, amiId)
   terraformShow(projectDirectory, planFile, redirectPlanFile)
   terraformApply(projectDirectory, variableFile, amiId)
   terraformDestroy(projectDirectory, variableFile, amiId)
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

def terraformValidate(directory) {
   dir(directory) {
      def terraformValidateCommand = "terraform validate"
      sh terraformValidateCommand 
   }   
}

def terraformPlan(project_dir, var_file, plan_file, ami_id) {
   dir(project_dir) {
     
      def terraformPlanCommand = "terraform plan -var=ami_id=$ami_id -var-file=../../vars/$var_file -out $plan_file"
      sh terraformPlanCommand
   }   
}

def terraformShow(project_dir, plan_file, redir_plan_file) {
   dir(project_dir) {
      def terraformShowCommand = "terraform show -json $plan_file | jq > $redir_plan_file"
      sh terraformShowCommand
   }   
}

def terraformApply(project_dir, var_file, ami_id) {
   dir(project_dir) {
      def terraformApplyCommand = "terraform apply -var=ami_id=$ami_id -var-file=../../vars/$var_file --auto-approve"
      sh terraformApplyCommand 
   }   
}

def terraformDestroy(project_dir, var_file, ami_id) {
   dir(project_dir) {
      def terraformDestroyCommand = "terraform destroy -var=ami_id=$ami_id -var-file=../../vars/${var_file} --auto-approve"

      sh terraformDestroyCommand
   }
}

