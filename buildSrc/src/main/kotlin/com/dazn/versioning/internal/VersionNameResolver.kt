package com.dazn.versioning.internal

import org.ajoberstar.grgit.Grgit

class VersionNameResolver(private val git: Grgit) : Resolver<String> {

    override fun resolve(): String {
        val currentBranch = git.branch.current().name

        if (GitBranch.isRelease(currentBranch)) {
            return currentBranch.split("/").last()
        }

        val lastTag = git.describe { it.commit = GitBranch.MASTER }
        val currentBranchNormalized = currentBranch.normalize()

        return "$lastTag-$currentBranchNormalized"
    }

    private fun String.normalize() =
        replace("[^a-zA-Z0-9]".toRegex(), "-")
}