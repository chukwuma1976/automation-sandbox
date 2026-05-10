pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        ENV = 'qa'
        CI = 'true'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/chukwuma1976/automation-sandbox.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dgroups=api'
            }
        }

        stage('Archive Reports') {
            steps {
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/**/*', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
        success {
            echo 'All tests passed.'
        }
        failure {
            echo 'Tests failed.'
        }
    }
}