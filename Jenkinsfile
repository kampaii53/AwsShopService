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
        stage('Docker pub'){
            steps{
                bat 'docker build . --tag=378642390019.dkr.ecr.us-east-1.amazonaws.com/kampaii:latest'
            }
        }
    }
}