pipelineJob('example-pipeline') {
    definition {
        cps {
            script(readFileFromWorkspace("/var/jenkins_home/workspace/Task-5/task_5/PipelineDefinition.groovy"))
            sandbox()
        }
    }
}
