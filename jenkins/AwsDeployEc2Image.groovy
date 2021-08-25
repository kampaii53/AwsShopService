pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages {
        stage('Build') {
            steps {
                withAWS(credentials: 'AwsShop', region: 'us-east-1') {
                    bat 'aws cloudformation create-stack ' +
                            '--stack-name aws-shop-ec2-instance ' +
                            '--template-body file://aws/docker-ec2-instance.yaml'
                    bat 'aws cloudformation wait stack-create-complete ' +
                            '--stack-name aws-shop-ec2-instance'
                }
            }
        }
    }
}