pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        ENV = 'qa'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/chukwuma1976/automation-sandbox.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
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