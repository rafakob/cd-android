package com.dazn.versioning.internal

import org.ajoberstar.grgit.Grgit

class VersionCodeResolver(private val git: Grgit) : Resolver<Int> {

    companion object {
        private const val BASE_CODE = 1000000
    }

    override fun resolve(): Int {
        val currentBranch = git.branch.current().name

        val numberOfCommits = when (GitBranch.isRelease(currentBranch)) {
            true -> countCommits(currentBranch)
            false -> countCommits(GitBranch.DEVELOP)
        }

        return BASE_CODE + numberOfCommits
    }

    private fun countCommits(branch: String) = git.log { it.includes = listOf(branch) }.size
}