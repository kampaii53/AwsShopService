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
                            'ParameterKey=SubnetID,ParameterValue=subnet-0f38d179aa3a374f9 ' +
                            'ParameterKey=SecurityGroup,ParameterValue=sg-023e14494ecf1fc37 ' +
                            'ParameterKey=ServiceName,ParameterValue=aws-shop ' +
                            'ParameterKey=ServiceVersion,ParameterValue=latest ' +
                            'ParameterKey=DockerHubUserName,ParameterValue=kampaii53'
                }
            }
        }
    }
}