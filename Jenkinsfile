pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'chmod +x gradlew'
                // This generates the build artifacts and the Jacoco XML report
                sh './gradlew clean build jacocoTestReport'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                // 'sonar-server' must match the name in Jenkins Global Config
                withSonarQubeEnv('sonar-server') {
                    sh './gradlew sonarqube'
                }
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', allowEmptyArchive: true
            }
        }
    }
}
