package com.dazn.versioning

import org.ajoberstar.grgit.Grgit
import org.gradle.api.Plugin
import org.gradle.api.Project

open class VersioningPlugin : Plugin<Project> {


    override fun apply(project: Project) {
        project.tasks.create<Greeting>("hello", Greeting::class.java) { task ->
            task.message = "Hello"
            task.recipient = "World"
        }

        val git = Grgit.open()




        project.extensions.create("repo", VersioningExtension::class.java, getVersionName(git), 132154)
    }

    private fun getVersionName(git: Grgit): String {
        val currentBranch = git.branch.current().name

        if (currentBranch.startsWith("release/").not()) {
            return "${git.describe { it.commit = "master" }}-${currentBranch.normalize()}"
        }

        return currentBranch.split("/").last()
    }

    private fun String.normalize() =
        replace("[^a-zA-Z0-9]".toRegex(), "-")
}