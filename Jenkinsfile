pipeline {

    agent any
    tools {
            maven 'mvn'
        }

    environment {
        workspace = pwd()
        branch = 'develop-docker'
        url = 'https://github.com/mudrek/ProjetoLivraria'
    }

    stages {
        stage('Checkout git') {
            steps {
                git branch: branch, credentialsId: 'jenkins', url: url
            }
        }

        stage('Build project') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Pull image') {
            steps {
                //sh "docker image prune -f"
                sh "docker build -t apirest ."
            }
        }

        stage('Remove old') {
            steps {
                sh "docker stop apirest || true && docker rm apirest || true"
            }
        }

        stage('Run container') {
            steps {
                sh "docker run -d --name apirest --restart always --volumes-from jenkins apirest"
            }
        }

    }

}
