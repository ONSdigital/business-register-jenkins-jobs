package project.pipeline.build

import uk.gov.ons.businessregister.constants.Constraints
import uk.gov.ons.businessregister.domain.Project

Project.values().groupBy({ it.type }).each { group ->
    def viewName = "${Constraints.projectFolder}/${group.key.viewName}"
    def jobNames = group.value.collect({ it.name }).toArray() as String[]

    listView(viewName) {
        columns {
            status()
            weather()
            name()
            lastSuccess()
            lastFailure()
            lastDuration()
            buildButton()
        }
        jobs {
            names(jobNames)
        }
    }
}
