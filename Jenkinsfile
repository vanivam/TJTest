#!/usr/bin/env groovy

import java.net.URL

node {
    
    stage('Git Checkout'){
        git 'https://github.com/vanivam/DevOpsClassCodes.git'
    }
    
    stage('Compile'){
        withMaven(maven:'M2_HOME'){
            sh 'mvn compile'
        }
    }
    
    stage('Review'){
        withMaven(maven:'M2_HOME'){
            sh 'mvn pmd:pmd'
        }
    }
    
    stage('Test'){
        try{
            withMaven(maven:'M2_HOME'){
                sh 'mvn test'
            }
        } finally {
            junit 'target/surefire-reports/*.xml'
        }
    }
    
    stage('CodeCoverage'){
        try{
            withMaven(maven:'M2_HOME'){
                sh 'mvn cobertura:cobertura -Dcobertura.report.format=xml'
            }
        } finally {
            cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: 'target/site/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
        }
    }
    
    stage('Package'){
        withMaven(maven:'M2_HOME'){
            sh 'mvn package'
        }
    }
}
