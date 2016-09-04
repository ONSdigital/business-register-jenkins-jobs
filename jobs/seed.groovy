// GitHub
def organisation = 'ONSDigital'
def defaultBranch = 'master'

job('Business Register - seed') {
    scm {
        github(organisation + '/business-register-jenkins-jobs', defaultBranch)
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        dsl {
            external 'jobs/**/jobs.groovy'
            additionalClasspath 'src/main/groovy'
        }
    }
}