package uk.gov.ons.businessregister

import javaposse.jobdsl.dsl.DslFactory
import uk.gov.ons.businessregister.constants.Constraints
import uk.gov.ons.businessregister.domain.Project

import static uk.gov.ons.businessregister.utils.StringUtils.removeWhitespaces

class JobBuilder {

    Project project;
    Project[] upstreamProjects;

    String buildCommand;
    String buildArtifact;

    void build(DslFactory factory) {
        factory.job(removeWhitespaces("${Constraints.projectFolder}/${project.name}")) {
            logRotator {
                numToKeep 5
                daysToKeep 10
            }
            scm {
                github(project.repository.location(), 'master')
            }
            triggers {
                scm('H/15 * * * 1-5')
                upstreamProjects.each { project ->
                    upstream(project.name, 'SUCCESS')
                }
            }
            steps {
                shell(buildCommand)
            }
            publishers {
                archiveArtifacts(buildArtifact)
            }
        }
    }

}
