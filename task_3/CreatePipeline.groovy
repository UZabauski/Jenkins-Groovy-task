import hudson.*
import jenkins.*
import hudson.model.* 
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.flow.FlowDefinition
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition

def jobPipe="""@Library('my-shared-lib') _
import org.foo.MyClass

MyClass a = new MyClass()

pipeline {
    agent {
        label 'master'
    }
    options {
        skipDefaultCheckout()
        timestamps()
    }
    stages {
        stage('Get Echo') {
            steps {
                script {
                    EasyFunc.myFunc()
                }
            }
        }
        stage('Get Random') {
            steps {
                script {
                    MathFunc.javaFunc()
                }
            }
        }
        stage('Check class') {
            steps {
                script {
                    a.fValue = 3
                    a.sValue = 6

                    assert a.math() == 9
                    a.itWorks()
                }
            }
        }
    }
}
"""

// Create and configure pipeline job
def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition(jobPipe, true)
def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(Jenkins.instance, "Task-3")
job.definition = flowDefinition
job.setConcurrentBuild(false)
job.save()
Jenkins.instance.reload()

//Build pipeline job
Jenkins jenkins = Jenkins.instance
String jobName = "Task-3"
def job2 = jenkins.getItem(jobName)
job2.scheduleBuild(0)
