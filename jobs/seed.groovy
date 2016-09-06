job('Business Register - seed') {
    scm {
        github('ONSDigital/business-register-jenkins-project.jobs', 'master')
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        dsl {
            external 'jobs/project/**/*.groovy'
            additionalClasspath 'src/main/groovy'
        }
    }
}