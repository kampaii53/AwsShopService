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
                            '--stack-name aws-shop-basic-service ' +
                            '--template-body file://aws/docker-image-deploy.yaml ' +
                            '--parameters ' +
                            'ParameterKey=NetworkStackName,ParameterValue=aws-shop-basic-network ' +
                            'ParameterKey=ServiceName,ParameterValue=aws-shop-app ' +
                            'ParameterKey=ImageUrl,ParameterValue=docker.io/kampaii53/aws-shop:latest ' +
                            'ParameterKey=ContainerPort,ParameterValue=8080'
                    bat 'aws cloudformation wait stack-create-complete ' +
                            '--stack-name aws-shop-basic-service'
                }
            }
        }
    }
}