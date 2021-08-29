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
                            '--stack-name cart-service ' +
                            '--template-body file://aws/cart-service.yaml ' +
                            '--capabilities CAPABILITY_NAMED_IAM'
                    bat 'aws cloudformation wait stack-create-complete ' +
                            '--stack-name cart-service'
                }
            }
        }
    }
}