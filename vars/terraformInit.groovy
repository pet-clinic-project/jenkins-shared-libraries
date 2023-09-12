def call(Map params) {
   def projectDirectory = params.projectDirectory

   terraformInit(projectDirectory)
}

def terraformInit(project_dir) {
   dir(project_dir) {
      def terraformInitCommand = "terraform init --reconfigure"
      sh terraformInitCommand 
   }   
}