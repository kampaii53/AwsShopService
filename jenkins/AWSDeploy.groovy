pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages {
        stage('Build') {
            environment {
                USER_CREDENTIALS = credentials('DockerHub')
            }
            steps {
                withAWS(credentials: 'AwsShop', region: 'us-east-1') {
                    bat 'echo $USER_CREDENTIALS_USR'
//                        bat 'aws cloudformation create-stack --stack-name awscli --template-body file://aws/example.yml'
                }
            }
        }
    }
}