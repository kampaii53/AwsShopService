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
                bat 'docker_build.sh'
            }
        }
    }
}