pipeline {
    agent any

    tools {
        maven 'Maven 3.8.6'
        jdk 'JDK 17'
    }

    stages {
        stage('Setup Chrome') {
            steps {
                sh '''
                    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
                    echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list
                    apt-get update
                    apt-get install -y google-chrome-stable

                    CHROME_VERSION=$(google-chrome --version | awk '{print $3}' | awk -F'.' '{print $1}')
                    wget -N https://chromedriver.storage.googleapis.com/LATEST_RELEASE_${CHROME_VERSION}
                    CHROMEDRIVER_VERSION=$(cat LATEST_RELEASE_${CHROME_VERSION})
                    wget -N https://chromedriver.storage.googleapis.com/${CHROMEDRIVER_VERSION}/chromedriver_linux64.zip
                    unzip -o chromedriver_linux64.zip
                    chmod +x chromedriver
                    mv chromedriver /usr/local/bin/
                '''
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            // Publish Cucumber reports
            cucumber buildStatus: 'SUCCESS',
                    reportTitle: 'Cucumber Report',
                    fileIncludePattern: '**/cucumber.json',
                    trendsLimit: 10

            // Archive test results
            junit '**/target/surefire-reports/*.xml'
        }
    }
}

