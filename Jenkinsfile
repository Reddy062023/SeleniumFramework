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

        stage('Run All Test Suites') {
            steps {
                script {
                    // Example: If you have multiple TestNG XML files
                    def suites = ['testng.xml'] // Add more XMLs if needed: ['suite1.xml','suite2.xml']

                    for (s in suites) {
                        echo "Running suite: ${s}"
                        bat "mvn test -DsuiteXmlFile=${s} -B"
                    }
                }
            }
        }

        stage('Combine Allure Results & Generate Report') {
            steps {
                script {
                    def allureHome = tool name: 'Allure', type: 'AllureCommandline'

                    withEnv(["PATH+ALLURE=${allureHome}\\bin"]) {

                        // Ensure combined results folder exists
                        bat 'if not exist target\\allure-results mkdir target\\allure-results'

                        // Copy results from multiple suites (if separate folders exist)
                        // Example: bat 'xcopy /E /I /Y module1\\target\\allure-results target\\allure-results'
                        // Add additional xcopy lines for other modules/suites as needed

                        // Preserve history
                        bat '''
                        if exist target\\allure-report\\history (
                            xcopy /E /I /Y target\\allure-report\\history target\\allure-results\\history
                        )
                        '''

                        // Generate single Allure report
                        bat 'allure generate target/allure-results -o target/allure-report --clean'

                        // Open report automatically
                        bat 'start target\\allure-report\\index.html'
                    }
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'

            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/allure-report/**/*', allowEmptyArchive: true

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/allure-report',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])

            cleanWs()
        }

        success {
            echo "Build, all test suites, and Allure report generation succeeded!"
        }

        failure {
            echo "Build or tests failed. Check console output and reports."
        }
    }
}
