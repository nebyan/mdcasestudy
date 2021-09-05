package com.casestudy

import com.casestudy.util.Validator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun userNameTest(){
        assertEquals(Validator.isUsernameValid("a"), false)
        assertEquals(Validator.isUsernameValid("ab"), false)
        assertEquals(Validator.isUsernameValid("abc"), true)
        assertEquals(Validator.isUsernameValid("ab123"), true)
    }

    @Test
    fun passwordTest(){
        assertEquals(Validator.isPasswordValid("abcdef"), false)
        assertEquals(Validator.isPasswordValid("ABCdef"), false)
        assertEquals(Validator.isPasswordValid("123456"), false)
        assertEquals(Validator.isPasswordValid("1234567"), false)
        assertEquals(Validator.isPasswordValid("12345a"), false)
        assertEquals(Validator.isPasswordValid("12345A"), true)
        assertEquals(Validator.isPasswordValid("12345Ab"), true)
        assertEquals(Validator.isPasswordValid("12345ab"), false)
        assertEquals(Validator.isPasswordValid("12345Ab!?*"), true)
    }
}