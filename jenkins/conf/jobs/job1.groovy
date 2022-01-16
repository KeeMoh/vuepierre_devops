println('-----------------------------------------Import Job CI/Job1')
def pipelineScript = new File('/var/jenkins_config/jobs/job1-pipeline.groovy').getText("UTF-8")

pipeLineJob('CI/job1') {
    description("Job Pipeline 1")
    parameters {
        stringParam {
            name('BRANCH')
            defaultValue('master')
            description("on git branch : master")
            trim(false)
        }
        booleanParam {
            name('SKIP_TEST')
            defaultValue(true)
        }
        choice {
            name('VERSION_TYPE')
            choices(['SNAPSHOT', 'RELEASE'])
        }
        stringParam {
            name('VERSION')
            defaultValue('JOB1-1.0-SNAPSHOT')
            description("JOB1-1.0-SNAPSHOT")
            trim(false)
        }
    }
    definition {
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}