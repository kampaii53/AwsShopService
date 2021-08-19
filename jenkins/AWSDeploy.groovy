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
                    bat 'aws cloudformation create-stack --stack-name aws-shop --template-body file://aws/ecs-simple.yaml' +
                            ' --parameters ' +
                            'ParameterKey=SubnetID,ParameterValue=subnet-09fded09be70a897d ' +
                            'ParameterKey=SecurityGroup,ParameterValue=sg-0f811a4c342c8bf6d ' +
                            'ParameterKey=ServiceName,ParameterValue=aws-shop ' +
                            'ParameterKey=ServiceVersion,ParameterValue=latest ' +
                            'ParameterKey=DockerHubUserName,ParameterValue=kampaii53'
                }
            }
        }
    }
}