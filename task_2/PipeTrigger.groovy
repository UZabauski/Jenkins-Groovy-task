import hudson.*
import jenkins.*
import hudson.model.* 
import hudson.tasks.Shell
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.flow.FlowDefinition
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition

// Create two FreeStyleJobs
["1", "2"].each {
	new_job = Jenkins.instance.createProject(FreeStyleProject, "Task-2.$it")
	new_job.buildersList.add(new Shell('echo "Hello World!"'))
	new_job.save()
}

def jobPipe="""import jenkins.model.*

stage "Triggering Jobs"
def buildJob1 = build job: 'Task-2.1', wait: true
def buildJob2 = build job: 'Task-2.2', wait: true
def result1 = buildJob1.currentResult
def result2 = buildJob2.currentResult
def genDescr = Jenkins.instance.getItemByFullName("Task-2")
currentBuild.description = "Task-2.1: " + result1 + System.lineSeparator() + "Task-2.2: " + result2
genDescr.setDescription("Last build Task-2.1: " + result1 + System.lineSeparator() + "Last build Task-2.2: " + result2)
genDescr.save()
"""

// Create and configure pipeline job
def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition(jobPipe, true)
def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(Jenkins.instance, "Task-2")
job.definition = flowDefinition
job.setConcurrentBuild(false)
job.save()
Jenkins.instance.reload()

// Build pipeline job
Jenkins jenkins = Jenkins.instance
String jobName = "Task-2"
def job2 = jenkins.getItem(jobName)
job2.scheduleBuild(0)
