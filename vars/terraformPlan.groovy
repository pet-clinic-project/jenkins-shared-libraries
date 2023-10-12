def call(Map params) {
   def projectDirectory = params.projectDirectory
   def variableFile = params.variableFile
   def planFile = params.planFile
   def redirectPlanFile = params.redirectPlanFile
   def amiId = params.amiId

   terraformPlan(projectDirectory, variableFile, planFile, amiId)
   terraformShow(projectDirectory, planFile, redirectPlanFile)
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
