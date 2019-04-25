package com.dazn.versioning

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class Greeting : DefaultTask() {
    var message: String? = null
    var recipient: String? = null

    @TaskAction
    internal fun sayGreeting() {
        System.out.printf("%s\n%s\n", message, recipient)
    }
}