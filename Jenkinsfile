pipeline {
    agent { label 'agent-windows' }

    environment {
        DOCKERHUB_USER = "encvr1"
        BACKEND_IMAGE  = "${DOCKERHUB_USER}/backend-commande"
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
                    bat "docker build -t %BACKEND_IMAGE%:%BACKEND_TAG% ./gestion_commande"
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    bat "docker-compose -f ./gestion_commande/docker-compose.yaml up -d --build"
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
