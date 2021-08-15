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
            steps {
                withDockerRegistry([credentialsId: "f42db408-f8db-4e38-a1cd-48caa7c67262", url: ""]) {
                    bat "docker push kampaii53/aws-shop:latest"
                }
            }
        }
    }
}