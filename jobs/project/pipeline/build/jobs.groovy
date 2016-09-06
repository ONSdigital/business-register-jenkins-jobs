package project.pipeline.build

import uk.gov.ons.businessregister.JobBuilder
import uk.gov.ons.businessregister.domain.Project

new JobBuilder(
        project: Project.TestUtils,
        buildCommand: './bin/sbt clean +publishLocal'
).build(this)

new JobBuilder(
        project: Project.BusinessLibs,
        upstreamProjects: [Project.TestUtils],
        buildCommand: './bin/sbt clean +publishLocal'
).build(this)

new JobBuilder(
        project: Project.BusinessIndexExporter,
        upstreamProjects: [Project.BusinessLibs],
        buildCommand: './bin/sbt clean assembly'
).build(this)

new JobBuilder(
        project: Project.BusinessDeduplicationTool,
        upstreamProjects: [Project.BusinessLibs],
        buildCommand: 'cd ons-business-dedupe-scala; ./bin/sbt clean assembly'
).build(this)

new JobBuilder(
        project: Project.BusinessDeduplicationResultAnalyser,
        buildCommand: './bin/sbt clean assembly'
).build(this)

new JobBuilder(
        project: Project.BusinessIndexApi,
        upstreamProjects: [Project.BusinessLibs],
        buildCommand: 'cd ons-business-api; ./bin/sbt clean dist'
).build(this)

new JobBuilder(
        project: Project.BusinessIndexFrontend,
        buildCommand: 'cd ons-business-frontend; ./bin/sbt clean dist'
).build(this)
