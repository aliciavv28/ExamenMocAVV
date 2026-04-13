pipeline {
    agent any

    tools {
        jdk 'jdk24'
        maven 'maven'
    }

    environment {
        APP_VERSION = "1.1.1"
    }

    stages {

        stage('Clonar repositorio') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[
                        url: 'https://github.com/aliciavv28/ExamenMocAVV.git'
                    ]]
                ])
            }
        }

        stage('Compilar proyecto') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Ejecutar tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generar paquete') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Guardar artefacto') {
            steps {
                script {
                    bat 'echo Preparando carpeta de versiones...'

                    bat '''
                        if exist versiones (
                            rmdir /s /q versiones
                        )
                        mkdir versiones
                    '''
                }
            }
            post {
                success {
                    bat """
                        copy target\\*%APP_VERSION%.jar versiones\\
                    """
                }
            }
        }
    }
}