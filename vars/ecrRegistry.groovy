import org.techiescamp.GlobalConfig

def call(Map params) {
  def imageName = params.imageName
  def repoName = params.repoName
  
  ecrLogin()
  dockerTagAndPush(imageName, repoName)
}

def ecrLogin() {
  def ecrLoginCommand = "aws ecr get-login-password --region ${GlobalConfig.awsRegion} | docker login --username AWS --password-stdin ${GlobalConfig.ecrRegistry}"
  sh ecrLoginCommand
}

def dockerTagAndPush(imageName, repoName) {
  def sourceImage = "$imageName:${GlobalConfig.versionTag}.${BUILD_NUMBER}"
  def targetImage = "${GlobalConfig.ecrRegistry}/$repoName:$imageName-${GlobalConfig.versionTag}.${BUILD_NUMBER}"

  def dockerTagCommand = "docker tag $sourceImage $targetImage"
  def dockerPushCommand = "docker push $targetImage"
  
  sh dockerTagCommand
  sh dockerPushCommand
}