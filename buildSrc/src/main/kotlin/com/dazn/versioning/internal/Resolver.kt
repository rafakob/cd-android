package com.dazn.versioning.internal

interface Resolver<T> {
    fun resolve(): T
}