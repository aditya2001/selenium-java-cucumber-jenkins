import groovy.json.JsonOutput

pipeline {
    //The agent section specifies where the entire Pipeline, or a specific stage,
    //will execute in the Jenkins environment depending on where the agent section is placed.
    agent any

    triggers {
        cron('H/30 * * * *')
    }

    options {
        timestamps()
        timeout(time: 20, unit: 'HOURS')
        ansiColor('xterm')
    }

//     parameters {
//          choice(name: 'CROSSBROWSER', choices: ['false', 'true'], description: 'Cross Browser Testing')
//          choice(name: 'BROWSER', choices: ['chrome', 'edge', 'firefox'], description: 'Pick the web browser you want to use to run your scripts')
//          choice(name: 'ENV', choices: ['uat', 'qa', 'dev'], description: 'Pick the env against which you need to run test')
//     }

    stages {
            stage('Parameters'){
                steps {
                    script {
                    properties([
                            parameters([
                                [$class: 'ChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the cross browser from the Dropdown List',
                                    filterLength: 1,
                                    filterable: false,
                                    name: 'CROSSBROWSER',
                                    script: [
                                        $class: 'GroovyScript',
                                        fallbackScript: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['Could not get the browser']"
                                        ],
                                        script: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['true','false']"
                                        ]
                                    ]
                                ],
                                [$class: 'CascadeChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the browser from drop down',
                                    name: 'BROWSER',
                                    referencedParameters: 'CROSSBROWSER',
                                    script:
                                        [$class: 'GroovyScript',
                                        fallbackScript: [
                                                classpath: [],
                                                sandbox: false,
                                                script: "return['Could not get Environment from Env Param']"
                                                ],
                                        script: [
                                                classpath: [],
                                                sandbox: false,
                                                script: '''
                                                if (CROSSBROWSER.equals("true")){
                                                    return[]
                                                }
                                                else if (CROSSBROWSER.equals("false")){
                                                    return["chrome", "firefox"]
                                                }
                                                '''
                                            ]
                                    ]
                                ]
                            ])
                        ])
                    }
                }
            }
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
                  return params.CROSSBROWSER == 'false'
                }
            }
          steps{
            bat "mvn test -DsuiteXmlFile=testng.xml -Dbrowser=${params.BROWSER} -Denv=${params.ENV}"
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

