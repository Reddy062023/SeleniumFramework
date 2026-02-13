pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Must match Jenkins global JDK tool name
        maven 'Maven'     // Must match Jenkins global Maven tool name
        allure 'Allure'   // Must match the Allure Commandline installation name in Jenkins
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
                // Run TestNG with suite XML and generate Surefire reports
                bat 'mvn clean test -DsuiteXmlFile=testng.xml -B'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo "Generating Allure report..."
                allure includeProperties: false, jdk: 'JDK17', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            echo "Publishing test results to Jenkins..."
            junit '**/target/surefire-reports/*.xml'  // TestNG results
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
            cleanWs()  // Optional: cleans workspace for next run
        }

        success {
            echo "Build and tests succeeded! Allure report should be available in the job."
        }

        failure {
            echo "Build or tests failed. Check console output and Allure report."
        }
    }
}
