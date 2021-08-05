#! groovy

import jenkins.model.Jenkins
import hudson.plugins.git.GitSCM
import javaposse.jobdsl.plugin.*
import hudson.triggers.SCMTrigger
import hudson.model.FreeStyleProject
import hudson.plugins.git.BranchSpec
import hudson.plugins.groovy.SystemGroovy
import hudson.plugins.git.UserRemoteConfig
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.domains.*
import hudson.plugins.groovy.FileSystemScriptSource
import hudson.plugins.groovy.StringSystemScriptSource
import org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.SecureGroovyScript;

// Configure credentials
Credentials cred = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,java.util.UUID.randomUUID().toString(), "", "username", "password")
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), cred)

def gitSeedCredentialsId = cred.id
def gitSeedScmURL = 'https://github.com/UZabauski/Jenkins-Groovy-task.git'

jenkins = Jenkins.instance
jobName = "Task-5"
branch = "*/main"

// Job System Groovy step configuration
SystemGroovy sg = new SystemGroovy(new FileSystemScriptSource("task_5/ScriptApproval.groovy"));
// SystemGroovy sg = new SystemGroovy(new StringSystemScriptSource(new SecureGroovyScript("println x", false, null)));

// Job DSL step configuration
dslBuilder = new ExecuteDslScripts()
dslBuilder.setTargets("**/DslJob.groovy, **/DslPipeline.groovy")
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
dslProject.getBuildersList().add(sg)
dslProject.getBuildersList().add(dslBuilder)

jenkins.add(dslProject, jobName)
