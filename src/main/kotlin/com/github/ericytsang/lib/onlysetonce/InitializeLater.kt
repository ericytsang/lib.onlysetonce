package com.github.ericytsang.lib.onlysetonce

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class InitializeLater<in Receiver,out Type,in InitParams>(initializer:Initializer<InitParams>,private val _initBlock:(InitParams)->Type):ReadOnlyProperty<Receiver,Type>
{
    init
    {
        initializer.initializees += this
    }

    private var isInitialized = false

    private val mutex = ReentrantLock()

    private var field:Type? = null

    fun initBlock(initParams:InitParams) = mutex.withLock()
    {
        check(!isInitialized)
        isInitialized = true
        field = _initBlock(initParams)
    }

    override fun getValue(thisRef:Receiver,property:KProperty<*>):Type = mutex.withLock()
    {
        check(isInitialized)
        return field as Type
    }
}
