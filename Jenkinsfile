pipeline {

    agent any
    tools {
            maven 'mvn'
        }

    environment {
        workspace = pwd()
        branch = 'develop-docker'
        url = 'https://github.com/mudrek/ProjetoLivraria'
        lastCommit = sh(script:'git log -1 --pretty=%B', returnStdout:true).trim()
        chatId = -323078175
    }

    stages {
        stage('Checkout git') {
            steps {
                git branch:branch, credentialsId:'jenkins', url:url
            }
        }

        stage('Build project') {
            steps {
                sh 'mvn clean install -DskipTests=true'
            }
        }

        stage('Pull image') {
            steps {
                sh "docker image prune -f"
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
                script {
                    sh "docker runn -d --name apirest --restart always -p 8080:8080 --network livrariainfra_database apirest"
                    currentBuild.result = "SUCCESS";
                }
            }
        }
    }
    post {
        success {
            script {
                echo "success"
                telegramSend(message: '#LIVRARIA: \n \t BRANCH: ' + branch + ' \n \t MESSAGE: Build realizado com sucesso' +
                    ' \n \t COMMIT MESSAGE: ' + lastCommit + ' \n \t STATUS: ONLINE \n \t', chatId: chatId)
            }
        }
        failure {
            script {
                echo "failure"
                    telegramSend(message: '#LIVRARIA: \n \t BRANCH: ' + branch + ' \n \t MESSAGE: Erro ao fazer build' +
                        ' \n \t COMMIT MESSAGE: ' + lastCommit + ' \n \t STATUS: OFFLINE \n \t', chatId: chatId)
            }
        }
    }

}
