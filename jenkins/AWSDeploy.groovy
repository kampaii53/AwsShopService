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
                    bat 'aws cloudformation create-stack --stack-name aws-shop-instances --template-body file://aws/ecs-simple.yaml' +
                            ' --parameters ' +
                            'ParameterKey=SubnetID,ParameterValue=subnet-039c2765c81c1d706 ' +
                            'ParameterKey=SubnetID2,ParameterValue=subnet-0044fa4777088bb26 ' +
                            'ParameterKey=SubnetID3,ParameterValue=subnet-0f917ea77eca849c3 ' +
                            'ParameterKey=SecurityGroup,ParameterValue=sg-015db395e7b6d76bf ' +
                            'ParameterKey=ServiceName,ParameterValue=aws-shop ' +
                            'ParameterKey=ServiceVersion,ParameterValue=latest ' +
                            'ParameterKey=DockerHubUserName,ParameterValue=kampaii53'
                }
            }
        }
    }
}