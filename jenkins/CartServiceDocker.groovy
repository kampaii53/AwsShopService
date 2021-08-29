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
        stage('Docker build cart-service') {
            steps {
                bat 'docker build ./shopping-cart-service --tag kampaii53/cart-service:latest'
            }
        }
        stage('Docker build order-service') {
            steps {
                bat 'docker build ./order-service --tag kampaii53/order-service:latest'
            }
        }
        stage('Docker push cart-service') {
            steps {
                withDockerRegistry([credentialsId: "f42db408-f8db-4e38-a1cd-48caa7c67262", url: ""]) {
                    bat "docker push kampaii53/cart-service:latest"
                }
            }
        }
        stage('Docker push order-service') {
            steps {
                withDockerRegistry([credentialsId: "f42db408-f8db-4e38-a1cd-48caa7c67262", url: ""]) {
                    bat "docker push kampaii53/order-service:latest"
                }
            }
        }
    }
}