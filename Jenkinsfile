node{
    stage('SCM checkout'){
        git 'https://github.com/kampaii53/AwsShopService'
    }
    stage('Compile-Package'){
        sh 'mvn package'
    }
}