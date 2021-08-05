#! groovy

import org.jenkinsci.plugins.scriptsecurity.scripts.*

def scriptApproval = ScriptApproval.get()

scriptApproval.approveSignature('method hudson.model.AbstractItem setDescription java.lang.String')
scriptApproval.approveSignature('method hudson.model.Saveable save')
scriptApproval.approveSignature('method jenkins.model.Jenkins getItemByFullName java.lang.String')
scriptApproval.approveSignature('method org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval approveScript java.lang.String')
scriptApproval.approveSignature('method org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval$PendingScript getHash')
scriptApproval.approveSignature('new java.io.File java.lang.String')
scriptApproval.approveSignature('new org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval$PendingScript java.lang.String org.jenkinsci.plugins.scriptsecurity.scripts.Language org.jenkinsci.plugins.scriptsecurity.scripts.ApprovalContext')
scriptApproval.approveSignature('staticMethod java.lang.System lineSeparator')
scriptApproval.approveSignature('staticMethod jenkins.model.Jenkins getInstance')
scriptApproval.approveSignature('staticMethod org.codehaus.groovy.runtime.DefaultGroovyMethods getText java.io.File')
scriptApproval.approveSignature('staticMethod org.jenkinsci.plugins.scriptsecurity.scripts.ApprovalContext create')
scriptApproval.approveSignature('staticMethod org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval get')
scriptApproval.approveSignature('staticMethod org.jenkinsci.plugins.scriptsecurity.scripts.languages.GroovyLanguage get')
