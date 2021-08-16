pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Docker build') {
            steps {
                bat 'docker build . --tag kampaii53/aws-shop:latest'
            }
        }
        stage('Docker push') {
            environment {
                DOCKER_HUB_LOGIN = credentials('DockerHub')
            }
            steps {
                bat 'echo $DOCKER_HUB_LOGIN'
//                withDockerRegistry([credentialsName: $]) {
//                    bat "docker push kampaii53/aws-shop:latest"
//                }
            }
        }
    }
}