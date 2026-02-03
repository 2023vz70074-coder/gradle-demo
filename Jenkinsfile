pipeline {
    agent any

    environment {
        GRADLE_VERSION = "8.7"
        GRADLE_HOME = "${env.WORKSPACE}/gradle-${GRADLE_VERSION}"
        PATH = "${GRADLE_HOME}/bin:${env.PATH}"
        SONAR_URL = "http://localhost:9000" 
    }

    stages {

        stage('Install Gradle') {
            steps {
                sh '''
                    if [ ! -d "$GRADLE_HOME" ]; then
                        echo "Downloading Gradle $GRADLE_VERSION..."
                        curl -s -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip
                        unzip -q gradle.zip
                        rm gradle.zip
                    fi
                '''
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                    gradle clean build
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_AUTH_TOKEN')]) {
                    sh '''
                        gradle sonar \
                          -Dsonar.host.url=${SONAR_URL} \
                          -Dsonar.login=${SONAR_AUTH_TOKEN} \
                          -Dsonar.gradle.skipCompile=true \
                          --no-configuration-cache
                    '''
                }
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }
}
