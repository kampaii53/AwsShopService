node{
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stage('Initialize'){
        sh '''
            echo "PATH = ${PATH}"
            echo "M2_HOME = ${M2_HOME}"
        '''
    }
    stage('SCM checkout'){
        git 'https://github.com/kampaii53/AwsShopService'
    }
    stage('Compile-Package'){
        sh 'mvn package'
    }
}