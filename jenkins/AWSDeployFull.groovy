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
                    bat 'aws cloudformation create-stack --stack-name aws-shop --template-body file://aws/vpc-private-public.yaml' +
                            ' --parameters ' +
                            'ParameterKey=EnvironmentName,ParameterValue=aws-shop'
                }
            }
        }
    }
}