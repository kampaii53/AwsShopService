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
                    bat 'aws cloudformation create-stack --stack-name aws-shop --template-body file://aws/ecs.yaml' +
                            ' --parameters ' +
                            'ParameterKey=SubnetID,ParameterValue=subnet-05d5c1a7255608ba5 ' +
                            'ParameterKey=ServiceName,ParameterValue=aws-shop ' +
                            'ParameterKey=ServiceVersion,ParameterValue=latest ' +
                            'ParameterKey=DockerHubUserName,ParameterValue=kampaii53'
                }
            }
        }
    }
}