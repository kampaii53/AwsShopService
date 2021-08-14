pipeline {
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages{
        stage('SCM checkout'){
            steps{
                git 'https://github.com/kampaii53/AwsShopService'
            }
        }
        stage('Compile-Package'){
            steps{
                sh 'mvn package'
            }
        }
    }
}