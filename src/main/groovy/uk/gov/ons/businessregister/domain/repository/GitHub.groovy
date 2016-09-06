package uk.gov.ons.businessregister.domain.repository

class GitHub implements Repository {
    final String organisation = 'ONSDigital'
    final String projectName;

    def GitHub(String projectName) {
        this.projectName = projectName
    }

    @Override
    String location() {
        return "$organisation/$projectName"
    }
}
