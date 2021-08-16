pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    environment {
        USER_CREDENTIALS = credentials('f42db408-f8db-4e38-a1cd-48caa7c67262')
    }
    stages {
        stage('Build') {
            steps {
                withAWS(credentials: 'AwsShop', region: 'us-east-1') {
                    bat 'echo ${USER_CREDENTIALS_USR}'
//                        bat 'aws cloudformation create-stack --stack-name awscli --template-body file://aws/example.yml'
                }
            }
        }
    }
}