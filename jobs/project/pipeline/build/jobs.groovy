package project.pipeline.build

import uk.gov.ons.businessregister.JobBuilder
import uk.gov.ons.businessregister.domain.Project

new JobBuilder(
        project: Project.TestUtils,
        buildCommand: './bin/sbt clean +publishLocal',
        buildArtifact: 'target/scala-2.*/test-utils_2.*.jar'
).build(this)

new JobBuilder(
        project: Project.BusinessLibs,
        upstreamProjects: [Project.TestUtils],
        buildCommand: './bin/sbt clean +publishLocal',
        buildArtifact: 'target/scala-2.*/business-core_2.*.jar'
).build(this)

new JobBuilder(
        project: Project.BusinessIndexExporter,
        upstreamProjects: [Project.BusinessLibs],
        buildCommand: './bin/sbt clean assembly',
        buildArtifact: 'target/scala-2.*/business-index-exporter-assembly-*.jar'
).build(this)

new JobBuilder(
        project: Project.BusinessDeduplicationTool,
        upstreamProjects: [Project.BusinessLibs],
        buildCommand: 'cd ons-business-dedupe-scala; ./bin/sbt clean assembly',
        buildArtifact: '*/target/scala-2.*/business-deduplication-tool-assembly-*.jar'
).build(this)

new JobBuilder(
        project: Project.BusinessDeduplicationResultAnalyser,
        buildCommand: './bin/sbt clean assembly',
        buildArtifact: 'target/scala-2.*/business-deduplication-result-analyser-assembly-*.jar'
).build(this)

new JobBuilder(
        project: Project.BusinessIndexApi,
        upstreamProjects: [Project.BusinessLibs],
        buildCommand: 'cd ons-business-api; ./bin/sbt clean dist',
        buildArtifact: '*/target/universal/business-index-api-*.zip'
).build(this)

new JobBuilder(
        project: Project.BusinessIndexFrontend,
        buildCommand: 'cd ons-business-frontend; ./bin/sbt clean dist',
        buildArtifact: '*/target/universal/business-index-frontend-*.zip'
).build(this)
