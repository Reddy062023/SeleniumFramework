pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Match your Jenkins global JDK
        maven 'Maven'     // Match your Jenkins global Maven
        allure 'Allure'   // Must match the Allure Commandline name in Jenkins Global Tool Config
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Reddy062023/SeleniumFramework.git'
            }
        }

        stage('Build') {
            steps {
                echo "Cleaning and compiling the project..."
                bat 'mvn clean compile -B'
            }
        }

        stage('Run Tests') {
            steps {
                echo "Running all TestNG suites..."
                // Run your testng.xml suite
                bat 'mvn test -DsuiteXmlFile=testng.xml -B'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo "Generating Allure report..."
                // Use results from target/allure-results
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            echo "Publishing TestNG results to Jenkins..."
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
            cleanWs() // Clean workspace for next run
        }

        success {
            echo "Build, tests, and Allure report succeeded!"
        }

        failure {
            echo "Build or tests failed. Check console output and Allure report."
        }
    }
}
