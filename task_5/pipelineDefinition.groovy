import jenkins.model.*

stage "Triggering Jobs"
def buildJob1 = build job: 'example-1', wait: true
def buildJob2 = build job: 'example-2', wait: true
def result1 = buildJob1.currentResult
def result2 = buildJob2.currentResult
def genDescr = Jenkins.instance.getItemByFullName("example-pipeline")
currentBuild.description = "example-1: " + result1 + System.lineSeparator() + "example-2: " + result2
genDescr.setDescription("Last build example-1: " + result1 + System.lineSeparator() + "Last build example-2: " + result2)
genDescr.save()
