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
       
    }

}
   