pipeline {
    agent any
    tools{ 
        maven 'Maven' 
      
    }
   
    stages{ 
         stage('Check Java Version') { 
            steps {
                sh 'java -version' 
                echo 'java version is'
            }
        }


        stage("Sonarqube Analysis "){ 
                    steps{
                        withSonarQubeEnv('sonar-server') {
                            sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=student \
                            -Dsonar.java.binaries=. \
                            -Dsonar.projectKey=student '''
                        }
                    }
                }


            stage("quality gate"){
                    steps {
                        script {
                          waitForQualityGate abortPipeline: false, credentialsId: 'Sonar-token'
                        }
                   }
                }
               
        stage("OWASP Dependency Check"){
            steps{
                dependencyCheck additionalArguments: '--scan ./ --format XML ', odcInstallation: 'DP-Check'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }



          stage('Build and start Docker Compose Containers') {
             steps {
                    sh 'docker-compose up -d'
                }
            }




            stage('Push New Tagged images to Docker Hub') {
                
            steps {
                    withCredentials([usernamePassword(credentialsId: 'secret', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD} 
                docker tag app-back azziiz/app-back:latest
                docker push azziiz/app-back:latest


                 docker tag app-front azziiz/app-front:latest
                docker push azziiz/app-front:latest


                
            '''
        }
    }
}
       
    }

}
   