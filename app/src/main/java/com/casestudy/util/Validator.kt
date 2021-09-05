package com.casestudy.util

import java.util.regex.Pattern

object Validator {

    val USERNAME_REGEX = "^.{3,}$"
    val PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])[A-Za-z[0-9]](.*){6,}$"

    fun isUsernameValid(userName: String): Boolean{
        return Pattern.matches(USERNAME_REGEX, userName)
    }

    fun isPasswordValid(password: String): Boolean{
        return Pattern.matches(PASSWORD_REGEX, password)
    }
}