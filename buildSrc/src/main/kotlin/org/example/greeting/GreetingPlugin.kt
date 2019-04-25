package org.example.greeting

import org.gradle.api.Plugin
import org.gradle.api.Project

open class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create<Greeting>("hello", Greeting::class.java) { task ->
            task.message = "Hello"
            task.recipient = "World"
        }
    }
}