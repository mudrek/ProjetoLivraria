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
                script{
                    try {
                        git branch:branch, credentialsId:'jenkins', url:url
                                lastCommit = sh(script:'git log -1 --pretty=%B', returnStdout:true).trim()
                    } catch (Exception e) {
                        currentBuild.result = "FAILURE";
                        lastCommit = sh(script:'git log -1 --pretty=%B', returnStdout:true).trim()
                        telegramSend (message: '#LIVRARIA: \n \t BRANCH: ' + branch + ' \n \t MESSAGE: Erro ao realizar checkout git.' +
                                ' \n \t COMMIT MESSAGE: ' + lastCommit + '\n \t ERROR MESSAGE: ' + e +
                                ' \n \t BUILD URL: (' + env.BUILD_URL + ') \n \t STATUS: OFFLINE ', chatId: -323078175)

                        throw e;
                    }
                }
            }
        }

        stage('Build project') {
            steps {
                script{
                    try {
                        sh 'mvn clean install -DskipTests=true'
                    } catch (Exception e) {
                        currentBuild.result = "FAILURE";
                        lastCommit = sh(script:'git log -1 --pretty=%B', returnStdout:true).trim()
                        telegramSend(message: '#LIVRARIA: \n \t BRANCH: ' + branch + ' \n \t MESSAGE: Erro ao realizar build do projeto.' +
                                ' \n \t COMMIT MESSAGE: ' + lastCommit + '\n \t ERROR MESSAGE: ' + e +
                                ' \n \t BUILD URL: (' + env.BUILD_URL + ') \n \t STATUS: OFFLINE ', chatId: -323078175)

                        throw e;
                    }
                }
            }
        }

        stage('Pull image') {
            steps {
                script{
                    try {
                        sh "docker image prune -f"
                        sh "docker build -t apirest ."
                    } catch (Exception e) {
                        currentBuild.result = "FAILURE";
                        lastCommit = sh(script:'git log -1 --pretty=%B', returnStdout:true).trim()
                        telegramSend (message: '#LIVRARIA: \n \t BRANCH: ' + branch + ' \n \t MESSAGE: Erro ao realizar pull da imagem.' +
                                ' \n \t COMMIT MESSAGE: ' + lastCommit + '\n \t ERROR MESSAGE: ' + e +
                                ' \n \t BUILD URL: (' + env.BUILD_URL + ') \n \t STATUS: OFFLINE ', chatId: -323078175)

                        throw e;
                    }
                }
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
                    try {
                        sh "docker run -d --name apirest --restart always -p 8080:8080 --network livrariainfra_database apirest"
                        lastCommit = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                        telegramSend(message: '#LIVRARIA: \n \t BRANCH: ' + branch + ' \n \t MESSAGE: Build realizado com sucesso' +
                        ' \n \t COMMIT MESSAGE: ' + lastCommit + ' \n \t STATUS: ONLINE \n \t', chatId: -323078175)
                    } catch (Exception e) {
                        currentBuild.result = "FAILURE";
                        lastCommit = sh(script: 'git log -1 --pretty=%B', returnStdout: true).trim()
                        telegramSend(message: '#LIVRARIA: \n \t BRANCH: ' + branch + ' \n \t MESSAGE: Erro ao realizar build' +
                        ' \n \t COMMIT MESSAGE: ' + lastCommit + '\n \t ERROR MESSAGE: ' + e +
                        ' \n \t BUILD URL: (' + env.BUILD_URL + ') \n \t STATUS: OFFLINE ', chatId: -323078175)

                        throw e;
                    }
                }
            }
        }

    }

}
