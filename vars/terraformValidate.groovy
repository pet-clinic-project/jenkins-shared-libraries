def call(Map params) {
   def projectDirectory = params.projectDirectory

   terraformValidate(projectDirectory)
}

def terraformValidate(project_dir) {
   dir(project_dir) {
      def terraformValidateCommand = "terraform validate"
      sh terraformValidateCommand 
   }   
}