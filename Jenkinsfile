node
{
  try {
   
    stage('checkout') {
      checkout scm
	    
    }
    stage('prepare') {
      sh "git clean -fdx"
	  commit_id = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
    }
    
    /* stage ('Initialize') {
        sh '''
		    
	    echo "JenkinsWorkspace = ${WORKSPACE}"
            echo "PATH = ${PATH}"
            echo "M2_HOME = ${M2_HOME}"
        '''
        }*/ 
    
	
    stage('package') {      
      sh 'mvn clean install -Dmaven.test.skip=true' 	  
    }
	  
    stage('docker publish') {
      echo "creating docker container..."
      docker.withRegistry('https://hybridcloudaccelerators.azurecr.io', 'hybrid-cloud-accelerators-acr') {
        	def customImage = docker.build("hybridcloudaccelerators.azurecr.io/eurekaservicemanagement:${commit_id}")
        	/* Push the container to the custom Registry */
		echo "Pushing Docker Container to Private Registry..."
        	customImage.push()
			sh "docker rmi -f hybridcloudaccelerators.azurecr.io/eurekaservicemanagement:${commit_id}"
	}
	
    }
	
	 
	 stage('Kubernetes Prod Deployment'){
	 withKubeConfig([credentialsId: 'testkube']) {
      /*sh "sed 's/\$COMMIT/${commit_id}/g' ${DEPLOY_YAML_FILE} | kubectl apply -f - -n hca-prod "
	 }
	 }
  stage('send success notification') {
	currentBuild.result = 'SUCCESS'*/
  }
  } 
  catch (err) {	
currentBuild.result = 'FAILURE'		
}
}
