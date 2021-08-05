import jenkins.model.Jenkins
import hudson.plugins.git.GitSCM
import javaposse.jobdsl.plugin.*
import hudson.triggers.SCMTrigger
import hudson.model.FreeStyleProject
import hudson.plugins.git.BranchSpec
import hudson.plugins.git.UserRemoteConfig
import com.cloudbees.plugins.credentials.*

// Credentials are already contained
def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
    com.cloudbees.plugins.credentials.common.StandardUsernameCredentials.class,
    Jenkins.instance,
    null,
    null
)
for (c in creds) {
  if (c.username == "username") {
       myCred = c.id
  }
}

def gitSeedCredentialsId = myCred
def gitSeedScmURL = 'https://github.com/UZabauski/Jenkins-Groovy-task.git'

jenkins = Jenkins.instance
jobName = "Task-4"
branch = "*/main"

// Job configuration
dslBuilder = new ExecuteDslScripts()
dslBuilder.setTargets("**/DslJob.groovy")
dslBuilder.setUseScriptText(false)
dslBuilder.setIgnoreExisting(false)
dslBuilder.setIgnoreMissingFiles(false)
dslBuilder.setRemovedJobAction(RemovedJobAction.DISABLE)
dslBuilder.setRemovedViewAction(RemovedViewAction.IGNORE)
dslBuilder.setLookupStrategy(LookupStrategy.SEED_JOB)

dslProject = new hudson.model.FreeStyleProject(jenkins, jobName)
dslProject.scm = new GitSCM(gitSeedScmURL)
dslProject.scm.branches = [new BranchSpec(branch)]
def config = new UserRemoteConfig(gitSeedScmURL, null, null, gitSeedCredentialsId)
dslProject.scm.userRemoteConfigs = [config]

dslProject.createTransientActions()
dslProject.getBuildersList().add(dslBuilder)

jenkins.add(dslProject, jobName)
