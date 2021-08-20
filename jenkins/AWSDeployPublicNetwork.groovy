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
                        '--stack-name aws-shop-basic-network ' +
                        '--template-body file://aws/single-public-network.yaml ' +
                        '--capabilities CAPABILITY_IAM'
                    bat 'aws cloudformation wait stack-create-complete ' +
                        '--stack-name aws-shop-basic-network'
                }
            }
        }
    }
}