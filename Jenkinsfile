pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'
        jdk 'JDK 17'
    }

    stages {
        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dselenium.grid.url=http://selenium-hub:4444/wd/hub'
            }
        }
    }

    post {
        always {
            cucumber buildStatus: 'SUCCESS',
                    reportTitle: 'Cucumber Report',
                    fileIncludePattern: '**/cucumber.json',
                    trendsLimit: 10

            junit '**/target/surefire-reports/*.xml'
        }
    }
}