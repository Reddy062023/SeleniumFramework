pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'Maven', type: 'maven'  // Your Jenkins Maven installation
        JAVA_HOME  = tool name: 'JDK17', type: 'jdk'    // Your JDK installation
        PATH       = "${env.MAVEN_HOME}/bin;${env.JAVA_HOME}/bin;${env.PATH}"
    }

    stages {

        // Checkout the code from GitHub
        stage('Checkout SCM') {
            steps {
                git(
                    url: 'https://github.com/Reddy062023/SeleniumFramework.git',
                    branch: 'main',
                    credentialsId: 'github-token'
                )
            }
        }

        // Build the Maven project
        stage('Build') {
            steps {
                bat "${MAVEN_HOME}/bin/mvn clean compile -B"
            }
        }

        // Run tests using the TestNG suite (including Allure listener)
        stage('Run Tests') {
            steps {
                bat "${MAVEN_HOME}/bin/mvn clean test -DsuiteXmlFile=testng.xml -B"
            }
        }

        // Generate Allure report if results are present
        stage('Generate Allure Report') {
            steps {
                script {
                    // Only generate Allure report if results exist
                    if (fileExists('target/allure-results')) {
                        echo "Generating Allure report..."
                        allure([
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'target/allure-results']],
                            includeProperties: true,
                            jdk: 'JDK17',               // Specify JDK for Allure
                            reportVersion: '2.21.0',    // Allure version
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

    // Post actions to clean workspace and provide feedback
    post {
        always {
            echo "Cleaning workspace..."
            cleanWs()
        }

        success {
            echo "Build and tests succeeded. Allure report ready!"
        }

        unstable {
            echo "Build is unstable. Check console output and Allure report."
        }

        failure {
            echo "Build failed! Check console output."
        }
    }
}
