package com.dazn.versioning.extensions


open class Versioning(
    var versionName: String,
    var versionCode: Int
) {
    companion object {
        const val NAME = "versioning"
    }
}