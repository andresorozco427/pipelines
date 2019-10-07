pipeline {	
	agent any
	
	options {
	    buildDiscarder(logRotator(numToKeepStr: '2'))
	    disableConcurrentBuilds()
	}			
	stages {
			stage('Checkout'){
				steps{				
					echo "...............CHECKOUT..............."
				 	  checkout([  
				 	  	$class: 'GitSCM',
				 	  	branches: [ [ name: '*/master' ] ],
				 	  	doGenerateSubmoduleConfigurations: false,
				 	    extensions: [],
                        submoduleCfg: [],
                        url:'https://github.com/andresorozco427/pipelines.git'])	          			 	  
				}				
			}	
			
			stage('Unit test'){
			    steps{
			        echo "..............Unit Test............."
			        bat 'gradle --b ./build.gradle test'
			    }	  
			}
			
			stage('Build'){
			    steps {
			    echo "...................Build................."
			  	  bat 'gradle --b ./build.gradle build -x test'
			    }
			}		
			
			stage('Analisis de codigo statico'){
				environment {
				    scannerHome = tool 'Scanner 4.2.0'
				}
			   steps{
	               echo '------------>Analisis de codigo estatico<------------'
	               withSonarQubeEnv('SonarQube') {
	                   bat "${scannerHome}/bin/sonar-scanner.bat"
	               }	               
	               timeout(time: 10, unit: 'MINUTES'){
	               		waitForQualityGate abortPipeline: true
	               }
	           }			
			}	
				
		}
		
		post {
			 always {
	             echo 'This will always run'
	         }
	         success {
	             echo 'This will run only if successful'
	             junit '**/build/test-results/test/*.xml'
	         }
		         failure {
	 		echo 'This will run only if failed' 
	 		mail (to: 'andres.orozco@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",
	 		body: "Something is wrongwith ${env.BUILD_URL}")
	 		}
	}

}	

