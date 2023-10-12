def call(Map params) {
   def projectDirectory = params.projectDirectory
   def variableFile = params.variableFile
   def amiId = params.amiId

   terraformDestroy(projectDirectory, variableFile, amiId)
}

def terraformDestroy(project_dir, var_file, ami_id) {
   dir(project_dir) {
      def terraformDestroyCommand = "terraform destroy -var=ami_id=$ami_id -var-file=../../vars/${var_file} --auto-approve"

      sh terraformDestroyCommand
   }
}
