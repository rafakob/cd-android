package com.dazn.versioning.internal

object GitBranch {
    const val DEVELOP = "develop"
    const val MASTER = "master"
    const val RELEASE = "release"

    fun isRelease(branch: String) = branch.startsWith("${GitBranch.RELEASE}/")
}