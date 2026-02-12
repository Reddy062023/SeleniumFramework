pipeline {
    agent any

    tools {
        jdk 'Java 17'
        maven 'Maven 3.9.2'
    }

    environment {
        HEADLESS = 'true'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Reddy062023/SeleniumFramework.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile -Dheadless=%HEADLESS%'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test -Dheadless=%HEADLESS%'
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
        }
    }
}
