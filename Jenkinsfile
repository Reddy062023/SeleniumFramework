pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Make sure this matches your Jenkins global tool
        maven 'Maven'     // Make sure this matches your Jenkins global tool
        allure 'Allure'   // Make sure Allure is installed in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Reddy062023/SeleniumFramework.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile -B'  // Clean compile before running tests
            }
        }

        stage('Run Tests') {
            steps {
                // Run TestNG tests and generate Surefire reports
                bat 'mvn clean test -B'  // Added clean for fresh runs
            }
        }

        stage('Generate Allure Report') {
            steps {
                // Generate Allure report from results
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            // Publish test results to Jenkins
            junit '**/target/surefire-reports/*.xml'  // Publish Surefire results
            // Archive Surefire XML files
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
            // Optional: clean workspace for next run
            cleanWs()
        }

        success {
            echo "Build and tests succeeded!"
        }

        failure {
            echo "Build or tests failed. Check console output and reports."
        }
    }
}
