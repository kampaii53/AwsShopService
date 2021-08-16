pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages {
        stage('Build') {
            environment{
                DOCKER_HUB_LOGIN = credentials('f42db408-f8db-4e38-a1cd-48caa7c67262')
            }
            steps {
                bat 'echo $DOCKER_HUB_LOGIN_USR'
//                withAWS(credentials: 'AwsShop', region: 'us-east-1') {
//                    bat 'aws cloudformation create-stack --stack-name awscli --template-body file://aws/example.yml'
//                }
            }
        }
    }
}