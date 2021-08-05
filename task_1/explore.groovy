import jenkins.*
import hudson.*
import hudson.model.* 
import jenkins.model.*
import hudson.tasks.Shell

// Create FreeStyleJob
new_job = Jenkins.instance.createProject(FreeStyleProject, 'Task-1')
new_job.buildersList.add(new Shell('echo "Hello World!"'))
new_job.save()

Jenkins.instance.getAllItems().each {
    // Execute job immediately
//    job.scheduleBuild(0)
    
    // Delete job
//      job.delete()

    // Get information about build
	def job = Jenkins.instance.getItemByFullName(it.fullName)
    def last_job_num = job.getLastBuild().getNumber()
	def build = job.getBuildByNumber(last_job_num)
	println('Job name: ' + it.fullName)
    println('Last build number: ' + last_job_num)
	println('Last build time: ' + job.getLastBuild().getTime())
	println('Last succesfull build: ' + job.getLastSuccessfulBuild())
	if (build.getCause(hudson.model.Cause.UserIdCause.class) != null) {
		println('Build started by ' + build.getCause(hudson.model.Cause.UserIdCause.class).getUserId())
    } else {
        println('Build started by Groovy Console')
    }
}
