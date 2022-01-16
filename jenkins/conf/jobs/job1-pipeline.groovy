pipeline {
    agent any
    tools {
        maven 'maven'
    }
    environment {
        TEST = 'TEST'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '100'))
        ansiColor('xterm')
    }
    stages {
        stage('Job description') {
            steps {
                script {
                    println('Env var: ' + env.TEST)
                    sh 'java --version'
                    sh 'mvn --version'
                    sh 'python3 --version'
                    currentBuild.displayName = "#${BUILD_NUMBER} ${params.PARAM1}"
                }
            }
        }
        stage('git branch') {
            steps {
                git branch: 'master', 
                url: 'https://github.com/Ozz007/sb3t'
            }
        }
        stage('mvn Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('mvn Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('mvn Verify') {
            steps {
                sh 'mvn verify'
            }
        }
        stage('mvn Install') {
            steps {
                sh 'mvn install'
            }
        }
    }
}