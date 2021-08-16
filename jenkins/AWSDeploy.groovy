pipeline{
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages {
        stage('Build') {
            steps {
                bat 'aws cloudformation create-stack --stack-name awscli --template-body /aws/example.yml'
            }
        }
    }
}