pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        VERSION = '1.0.0'
    }

    stages {
        // Stage 1: Checkout
        stage('Checkout') {
            steps {
                echo 'Clonando repositorio desde GitHub...'
                checkout scm
            }
        }

        // Stage 2: Build
        stage('Build') {
            steps {
                echo 'Compilando proyecto...'
                withMaven(maven: 'maven') {
                    bat 'mvn clean compile'
                }
            }
        }

        // Stage 3: Test
        stage('Test') {
            steps {
                echo 'Ejecutando tests...'
                withMaven(maven: 'maven') {
                    bat 'mvn test'
                }
            }
        }

        // Stage 4: Package
        stage('Package') {
            steps {
                echo 'Empaquetando proyecto...'
                withMaven(maven: 'maven') {
                    bat 'mvn package'
                }
            }
        }

        // Stage 5: Move jar
        stage('Move jar') {
            steps {
                echo 'Eliminando directorio versiones....'

                // Borra la carpeta versiones si existe
                bat '''
                if exist versiones (
                    rmdir /s /q versiones
                )
                '''

            }
            post {
                success {
                    echo 'Se crea el directorio versiones con la última versión de la api'
                    bat '''
                    mkdir versiones
                    copy target\\*-${VERSION}.jar versiones\\
                    '''
                }
            }
        }
    }
}