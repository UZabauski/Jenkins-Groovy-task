import jenkins.model.Jenkins
import jenkins.plugins.git.GitSCMSource
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.domains.*
import jenkins.plugins.git.traits.BranchDiscoveryTrait
import org.jenkinsci.plugins.workflow.libs.GlobalLibraries
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever

// Configure credentials
Credentials cred = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,java.util.UUID.randomUUID().toString(), "", "user", "password")
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), cred)

// Configure Global Shared Library
List libraries = [] as ArrayList

def remote = "https://github.com/UZabauski/Jenkins-Groovy-task.git"
def credentialsId = cred.id

name = 'my-shared-lib'
defaultVersion = 'shared-library'

def scm = new GitSCMSource(remote)
if (credentialsId != null) {
    scm.credentialsId = credentialsId
}

scm.traits = [new BranchDiscoveryTrait()]
def retriever = new SCMSourceRetriever(scm)

def library = new LibraryConfiguration(name, retriever)
library.defaultVersion = defaultVersion
library.implicit = false
library.allowVersionOverride = true
library.includeInChangesets = true

libraries << library

def global_settings = Jenkins.instance.getExtensionList(GlobalLibraries.class)[0]
global_settings.libraries = libraries
global_settings.save()
println 'Configured Pipeline Global Shared Libraries:\n    ' + global_settings.libraries.collect { it.name }.join('\n    ')
