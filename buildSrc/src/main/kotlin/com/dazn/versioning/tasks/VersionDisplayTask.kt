package com.dazn.versioning.tasks

import com.dazn.versioning.extensions.Versioning
import com.dazn.versioning.internal.GitBranch
import org.ajoberstar.grgit.Grgit
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class VersionDisplayTask : DefaultTask() {

    companion object {
        const val NAME = "versionDisplay"
    }

    lateinit var git: Grgit
    lateinit var versioning: Versioning

    @TaskAction
    fun run() {
        separator()
        println("[Git] lastTag = ${git.describe { it.commit = GitBranch.MASTER }}")
        println("[Git] branch  = ${git.branch.current().name}")
        println("[Git] commit  = ${git.log().last().abbreviatedId}")
        separator()
        println("[Versioning] versionName = ${versioning.versionName}")
        println("[Versioning] versionCode = ${versioning.versionCode}")
        separator()

    }

    private fun separator() = println("----------------------------------------------------------")
}