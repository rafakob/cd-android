package com.dazn.versioning

import com.dazn.versioning.extensions.Versioning
import com.dazn.versioning.internal.Resolver
import com.dazn.versioning.internal.VersionCodeResolver
import com.dazn.versioning.internal.VersionNameResolver
import com.dazn.versioning.tasks.VersionDisplayTask
import org.ajoberstar.grgit.Grgit
import org.gradle.api.Plugin
import org.gradle.api.Project

open class VersioningPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val git = Grgit.open()
        val versionNameResolver: Resolver<String> = VersionNameResolver(git)
        val versionCodeResolver: Resolver<Int> = VersionCodeResolver(git)

        val versioning =
            project.extensions.create(
                Versioning.NAME,
                Versioning::class.java,
                versionNameResolver.resolve(),
                versionCodeResolver.resolve()
            )

        project.tasks.create<VersionDisplayTask>(
            VersionDisplayTask.NAME,
            VersionDisplayTask::class.java
        ) {
            it.git = git
            it.versioning = versioning
        }
    }
}