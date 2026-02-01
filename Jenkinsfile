pipeline {
    agent any

    environment {
        DOCKERHUB_USER = "encvr1"
        BACKEND_IMAGE  = "${DOCKERHUB_USER}/backend-courrier"
        BACKEND_TAG    = "1.${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                script {
                    sh "docker build -t ${BACKEND_IMAGE}:${BACKEND_TAG} ."
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sh "docker compose up -d --build"
                }
            }
        }
    }

    post {
        success {
            echo "✅ Déploiement réussi"
        }
        failure {
            echo "❌ Le pipeline a échoué, vérifie les logs Jenkins."
        }
    }
}
