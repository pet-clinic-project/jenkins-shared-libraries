def call(Map params) {
   def projectDirectory = params.projectDirectory
   def variableFile = params.variableFile
   def planFile = params.planFile
   def planFileJson = params.planFileJson

   terraformPlan(projectDirectory, variableFile, planFile)
   terraformShow(projectDirectory, planFile, planFileJson)
}

def terraformPlan(project_dir, var_file, plan_file) {
   dir(project_dir) {
      def terraformPlanCommand = "terraform plan -var-file=../../../vars/dev/$var_file -out $plan_file"
      sh terraformPlanCommand
   }   
}

def terraformShow(project_dir, plan_file, plan_file_json) {
   dir(project_dir) {
      def terraformShowCommand = "terraform show -json $plan_file | jq > $plan_file_json"
      sh terraformShowCommand
   }   
}