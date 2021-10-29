pipeline {
    agent any
    tools {
        maven '3.8.2'
        jdk 'java8'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    properties([
                            parameters([
                                    choice(
                                            choices: ['ONE', 'TWO'],
                                            name: 'PARAMETER_01'
                                    ),
                                    booleanParam(
                                            defaultValue: true,
                                            description: '',
                                            name: 'BOOLEAN'
                                    ),
                                    text(
                                            defaultValue: '''
                                this is a multi-line 
                                string parameter example
                                ''',
                                            name: 'MULTI-LINE-STRING'
                                    ),
                                    string(
                                            defaultValue: 'scriptcrunch',
                                            name: 'STRING-PARAMETER',
                                            trim: true
                                    )
                            ])
                    ])
                }
            }
        }
    }
}