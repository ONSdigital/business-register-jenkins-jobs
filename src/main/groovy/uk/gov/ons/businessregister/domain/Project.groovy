package uk.gov.ons.businessregister.domain

import uk.gov.ons.businessregister.domain.repository.GitHub
import uk.gov.ons.businessregister.domain.repository.Repository

enum Project {
    TestUtils(
            ProjectType.LIBRARY, 'test-utils',
            new GitHub('business-register-test-utils')
    ),
    BusinessLibs(
            ProjectType.LIBRARY, 'business-libs',
            new GitHub('business-register-business-libs')
    ),
    BusinessIndexExporter(
            ProjectType.TOOL, 'business-index-exporter',
            new GitHub('business-register-business-index-exporter')
    ),
    BusinessDeduplicationTool(
            ProjectType.TOOL, 'business-deduplication-tool',
            new GitHub('ons-business')
    ),
    BusinessDeduplicationResultAnalyser(
            ProjectType.TOOL, 'business-deduplication-result-analyser',
            new GitHub('business-register-business-deduplication-result-analyser')
    ),
    BusinessIndexApi(
            ProjectType.WEB_APPLICATION, 'business-index-api',
            new GitHub('ons-business')
    ),
    BusinessIndexFrontend(
            ProjectType.WEB_APPLICATION, 'business-index-frontend',
            new GitHub('ons-business')
    )

    final ProjectType type;
    final String name;

    final Repository repository;

    private Project(ProjectType type, String name, Repository repository) {
        this.type = type
        this.name = name

        this.repository = repository
    }
}

enum ProjectType {
    LIBRARY('LIBRARIES'), TOOL('TOOLS'), WEB_APPLICATION('WEB APPLICATIONS')

    final String viewName;

    private ProjectType(String viewName) {
        this.viewName = viewName
    }
}