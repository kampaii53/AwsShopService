pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages{
        stage('Build'){
            steps{
                sh 'mvn install'
            }
        }
    }
}