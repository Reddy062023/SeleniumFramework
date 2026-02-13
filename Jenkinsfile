pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
        allure 'Allure'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Reddy062023/SeleniumFramework.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile -B'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test -DsuiteXmlFile=testng.xml -B'
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    def resultsDir = "${env.WORKSPACE}\\target\\allure-results"
                    if (fileExists(resultsDir)) {
                        echo "Generating Allure report..."
                        allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                    } else {
                        echo "Warning: Allure results folder not found! Skipping report."
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Publishing TestNG results..."
            junit '**/target/surefire-reports/*.xml'

            echo "Archiving Allure report..."
            archiveArtifacts artifacts: 'allure-report/**', allowEmptyArchive: true

            cleanWs()
        }

        success {
            echo "Build and tests succeeded!"
        }

        failure {
            echo "Build or tests failed. Check console output and Allure report."
        }
    }
}
