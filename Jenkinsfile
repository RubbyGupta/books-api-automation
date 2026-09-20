pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    options {
        skipDefaultCheckout(true)
        timestamps()
    }

    stages {
        stage('Checkout code') {
            steps {
                checkout scm
            }
        }

        stage('Check tools') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Run API tests and generate reports') {
            steps {
                sh 'mvn -B clean verify'
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/TEST-*.xml',
                  allowEmptyResults: false

            archiveArtifacts artifacts: 'target/cucumber-html-reports/**,target/cucumber-report.*',
                             allowEmptyArchive: true
        }
    }
}