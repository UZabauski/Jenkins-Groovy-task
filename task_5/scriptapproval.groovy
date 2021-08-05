#! groovy

import org.jenkinsci.plugins.scriptsecurity.scripts.*
import org.jenkinsci.plugins.scriptsecurity.scripts.languages.GroovyLanguage

final ScriptApproval sa = ScriptApproval.get()
def scripts = ["/var/jenkins_home/workspace/Task-5/task_4/DslJob.groovy", "/var/jenkins_home/workspace/Task-5/task_5/DslPipeline.groovy"]
for (script in scripts) {
  String file = new File(script).text
  ScriptApproval.PendingScript s = new ScriptApproval.PendingScript(file, GroovyLanguage.get(), ApprovalContext.create())
  sa.approveScript(s.getHash())
}
