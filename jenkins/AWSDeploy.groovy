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
                            ' --parameters SubnetID=subnet-05d5c1a7255608ba5,ServiceName=aws-shop,ServiceVersion=latest,DockerHubUserName=kampaii53'
                }
            }
        }
    }
}