package util

import org.junit.Test
import i_introduction._1_Java_To_Kotlin_Converter._01_Functions
import junit.framework.Assert
import i_introduction._3_Default_Arguments._03_Default_Arguments
import i_introduction._4_Lambdas._04_Lambdas
import i_introduction._7_Nullable_Types._07_Nullable_Types
import i_introduction._8_Smart_Casts._06_Smart_Casts
import ii_collections._24_Extensions_On_Collections

class AdditionalTest {
    @Test fun test() {
        invokeTests(
                { _01_Functions().collection() },
                { _03_Default_Arguments().testDefaultAndNamedParams() },
                { _04_Lambdas().contains() },
                { _07_Nullable_Types().everythingIsOk() },
                { _06_Smart_Casts().testNum() },
                { _24_Extensions_On_Collections().testCollectionOfOneElement() }
        )
        Assert.assertTrue("${JavaCode.set}", JavaCode.set.isEmpty())
    }

    private fun invokeTests(vararg tests: () -> Unit) {
        for (test in tests) {
            try {
                test()
            }
            catch (e: NotImplementedException) {}
        }
    }
}

