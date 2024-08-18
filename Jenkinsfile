import groovy.json.JsonOutput

pipeline {
    //The agent section specifies where the entire Pipeline, or a specific stage,
    //will execute in the Jenkins environment depending on where the agent section is placed.
    agent any

    triggers {
        cron('TZ=America/New_York\n30 0 * * *')
    }

    options {
        timestamps()
        timeout(time: 20, unit: 'HOURS')
        ansiColor('xterm')
    }

    parameters {
         choice(name: 'BROWSER', choices: ['chrome', 'edge', 'firefox'], description: 'Pick the web browser you want to use to run your scripts')
    }

    stages {
        stage('Building'){
           steps {
             echo "Building the application"
//               checkout scmGit(
//                     branches: [[name: "master"]],
//                     userRemoteConfigs: [[credentialsId: 'ssh-keys',
//                         url: 'git@github.com:aditya2001/selenium-java-cucumber.git']])
             }
          }
        stage('Testing'){
          when {
              expression {
                  return params.BROWSER == 'chrome'
                }
            }
          steps{
            bat "mvn test -DsuiteXmlFile=testng.xml -Dbrowser=${params.BROWSER}"
          }
        }
        stage('Deploying'){
        steps{
          echo "Deploying"
        }
      }
     }

        post {
             always {
                 archiveArtifacts artifacts: "test output/PdfReport/ExtentPdf.pdf", onlyIfSuccessful: false
                 publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'test output/PdfReport', reportFiles: 'ExtentPdf.pdf', reportName: 'PDF Report', reportTitles: ''])
             }
         }
 }

