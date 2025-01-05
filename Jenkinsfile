pipeline {
    agent any
    tools{ 
        maven 'Maven 3' 
      
    }
   
    stages{ 
         stage('Check Java Version') { 
            steps {
                sh 'java -version' 
                echo 'java version is'
            }
        }
          stage('Build and start Docker Compose Containers') {
             steps {
                    sh 'docker-compose up '
                }
            }

            stage('Push New Tag to Docker Hub') {
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
   