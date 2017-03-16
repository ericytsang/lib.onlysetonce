package com.github.ericytsang.lib.onlysetonce

class Initializer<InitParams>
{
    internal val initializees = mutableSetOf<InitializeLater<*,*,InitParams>>()
    fun initialize(initParams:InitParams)
    {
        initializees.forEach {it.initBlock(initParams)}
    }
}
