pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages{
        stage('Build'){
            steps{
                bat 'mvn clean package'
            }
        }
        stage('Docker build'){
            steps{
                bat 'docker build . --tag=kampaii:latest'
            }
        }
    }
}