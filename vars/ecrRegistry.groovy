import org.techiescamp.GlobalConfig

def call(Map params) {
  def imageName = params.imageName
  def repoName = params.repoName
  def buildName = params.buildName
  
  ecrLogin()
  dockerTagAndPush(imageName, repoName, buildName)
}

def ecrLogin() {
  def ecrLoginCommand = "aws ecr get-login-password --region ${GlobalConfig.SubConfig.awsRegion} | docker login --username AWS --password-stdin ${GlobalConfig.SubConfig.ecrRegistry}"
  sh ecrLoginCommand
}

def dockerTagAndPush(imageName, repoName, buildNumber) {
  def sourceImage = "$imageName:${GlobalConfig.versionTag}.$buildNumber"
  def targetImage = "${GlobalConfig.SubConfig.ecrRegistry}/$repoName:$imageName-${GlobalConfig.versionTag}.$buildNumber"

  def dockerTagCommand = "docker tag $sourceImage $targetImage"
  def dockerPushCommand = "docker push $targetImage"
  
  sh dockerTagCommand
  sh dockerPushCommand
}