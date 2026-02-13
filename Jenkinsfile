pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven', type: 'maven'
        JAVA_HOME  = tool name: 'JDK17', type: 'jdk'
        PATH       = "${env.MAVEN_HOME}/bin;${env.JAVA_HOME}/bin;${env.PATH}"
    }

    stages {

        stage('Checkout SCM') {
            steps {
                git(
                    url: 'https://github.com/Reddy062023/SeleniumFramework.git',
                    branch: 'main',
                    credentialsId: 'github-token'
                )
            }
        }

        stage('Build') {
            steps {
                bat "${MAVEN_HOME}/bin/mvn clean compile -B"
            }
        }

        stage('Run Tests') {
            steps {
                bat "${MAVEN_HOME}/bin/mvn test -B"
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    if (fileExists('target/allure-results')) {
                        echo "Generating Allure report..."
                        allure([
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']],
                            includeProperties: true,
                            jdk: 'JDK17',
                            reportVersion: '2.21.0',
                            properties: []
                        ])
                        echo "Allure report successfully generated!"
                    } else {
                        echo "Warning: Allure results folder not found. Skipping report."
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Cleaning workspace..."
            cleanWs()
        }
        success {
            echo "Build and tests succeeded. Allure report ready!"
        }
        failure {
            echo "Build failed! Check console output."
        }
    }
}
