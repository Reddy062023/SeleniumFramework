pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven-3.9.4'   // Update with your Maven tool name in Jenkins
        ALLURE_HOME = tool 'Allure'       // Allure CLI installed in Jenkins
        PATH = "${env.MAVEN_HOME}/bin;${env.ALLURE_HOME}/bin;${env.PATH}"
    }

    stages {

        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat "mvn clean compile -B"
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn test -DsuiteXmlFile=testng.xml -B"
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    if (fileExists('target/allure-results')) {
                        echo "Generating Allure report..."
                        bat "allure generate target/allure-results -o allure-report --clean"
                        echo "Allure report generated at allure-report"
                    } else {
                        echo "Warning: Allure results folder not found! Skipping report generation."
                    }
                }
            }
        }

        stage('Publish Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'allure-report/**', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo "Build, tests, and Allure report succeeded!"
        }
        unstable {
            echo "Build is unstable. Check test results or Allure report."
        }
        failure {
            echo "Build failed! Check console output."
        }
    }
}
