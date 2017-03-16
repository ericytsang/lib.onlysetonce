package com.github.ericytsang.lib.onlysetonce.test

import com.github.ericytsang.lib.onlysetonce.InitializeLater
import com.github.ericytsang.lib.onlysetonce.Initializer
import org.junit.Test

class InitializeLaterTest
{
    val initializer = Initializer<String>()

    val lateInitString by InitializeLater(initializer)
    {
        it
    }

    @Test
    fun initializeThenGetTest()
    {
        initializer.initialize("this is the string")
        println(lateInitString)
    }

    @Test
    fun getBeforeInitializeTest()
    {
        try
        {
            println(lateInitString)
            throw AssertionError()
        }
        catch (ex:AssertionError)
        {
            throw ex
        }
        catch (ex:Exception)
        {
            RuntimeException(ex).printStackTrace(System.out)
        }
    }
}
