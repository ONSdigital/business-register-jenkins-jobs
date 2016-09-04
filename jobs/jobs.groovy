def project = 'Business Register'

// GitHub
def organisation = 'ONSDigital'
def defaultBranch = 'master'

// folders

def projectFolder = project
folder(projectFolder)

// jobs

job(projectFolder + '/LIBRARY - test-utils') {
    scm {
        github(organisation + '/business-register-test-utils', defaultBranch)
    }
    triggers {
        scm('H/15 * * * 1-5')
    }
    steps {
        shell('./bin/sbt clean +publishLocal')
    }
}

job(projectFolder + '/LIBRARY - business-libs') {
    scm {
        github(organisation + '/business-register-business-libs', defaultBranch)
    }
    triggers {
        scm('H/15 * * * 1-5')
        upstream('LIBRARY - test-utils', 'SUCCESS')
    }
    steps {
        shell('./bin/sbt clean +publishLocal')
    }
}

job(projectFolder + '/TOOL - business-index-exporter') {
    scm {
        github(organisation + '/business-register-business-index-exporter', defaultBranch)
    }
    triggers {
        scm('H/15 * * * 1-5')
        upstream('LIBRARY - business-libs', 'SUCCESS')
    }
    steps {
        shell('./bin/sbt "set test in assembly := {}" clean assembly')
    }
}

job(projectFolder + '/TOOL - business-deduplication-tool') {
    scm {
        github(organisation + '/ons-business', defaultBranch)
    }
    triggers {
        scm('H/15 * * * 1-5')
        upstream('LIBRARY - business-libs', 'SUCCESS')
    }
    steps {
        shell('cd ons-business-dedupe-scala; ./bin/sbt "set test in assembly := {}" clean assembly')
    }
}

job(projectFolder + '/TOOL - business-deduplication-result-analyser') {
    scm {
        github(organisation + '/business-register-business-deduplication-result-analyser', defaultBranch)
    }
    triggers {
        scm('H/15 * * * 1-5')
    }
    steps {
        shell('./bin/sbt "set test in assembly := {}" clean assembly')
    }
}

job(projectFolder + '/WEB APPLICATION - business-index-api') {
    scm {
        github(organisation + '/ons-business', defaultBranch)
    }
    triggers {
        scm('H/15 * * * 1-5')
        upstream('LIBRARY - business-libs', 'SUCCESS')
    }
    steps {
        shell('cd ons-business-api; ./bin/sbt clean dist')
    }
}

job(projectFolder + '/WEB APPLICATION - business-index-frontend') {
    scm {
        github(organisation + '/ons-business', defaultBranch)
    }
    triggers {
        scm('H/15 * * * 1-5')
    }
    steps {
        shell('cd ons-business-frontend; ./bin/sbt "set Test " clean dist')
    }
}
