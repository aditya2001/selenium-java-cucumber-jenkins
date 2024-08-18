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
                                  [$class: 'ExtendedChoiceParameterValue ',
                                      choiceType: 'PT_SINGLE_SELECT',
                                      description: 'Select the Environemnt from the Dropdown List',
                                      filterLength: 1,
                                      filterable: false,
                                      name: 'Env',
                                      script: [
                                          $class: 'GroovyScript',
                                          fallbackScript: [
                                              classpath: [],
                                              sandbox: false,
                                              script:
                                                  "return['Could not get The environemnts']"
                                          ],
                                          script: [
                                              classpath: [],
                                              sandbox: false,
                                              script:
                                                  "return['dev','stage','prod']"
                                          ]
                                      ]
                                  ],
                                  [$class: 'CascadeChoiceParameter',
                                      choiceType: 'PT_SINGLE_SELECT',
                                      description: 'Select the AMI from the Dropdown List',
                                      name: 'AMI List',
                                      referencedParameters: 'Env',
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
                                                  if (Env.equals("dev")){
                                                      return["ami-sd2345sd", "ami-asdf245sdf", "ami-asdf3245sd"]
                                                  }
                                                  else if(Env.equals("stage")){
                                                      return["ami-sd34sdf", "ami-sdf345sdc", "ami-sdf34sdf"]
                                                  }
                                                  else if(Env.equals("prod")){
                                                      return["ami-sdf34sdf", "ami-sdf34ds", "ami-sdf3sf3"]
                                                  }
                                                  '''
                                              ]
                                      ]
                                  ],
                                  [$class: 'DynamicReferenceParameter',
                                      choiceType: 'ET_ORDERED_LIST',
                                      description: 'Select the  AMI based on the following information',
                                      name: 'Image Information',
                                      referencedParameters: 'Env',
                                      script:
                                          [$class: 'GroovyScript',
                                          script: 'return["Could not get AMi Information"]',
                                          script: [
                                              script: '''
                                                      if (Env.equals("dev")){
                                                          return["ami-sd2345sd:  AMI with Java", "ami-asdf245sdf: AMI with Python", "ami-asdf3245sd: AMI with Groovy"]
                                                      }
                                                      else if(Env.equals("stage")){
                                                          return["ami-sd34sdf:  AMI with Java", "ami-sdf345sdc: AMI with Python", "ami-sdf34sdf: AMI with Groovy"]
                                                      }
                                                      else if(Env.equals("prod")){
                                                          return["ami-sdf34sdf:  AMI with Java", "ami-sdf34ds: AMI with Python", "ami-sdf3sf3: AMI with Groovy"]
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

