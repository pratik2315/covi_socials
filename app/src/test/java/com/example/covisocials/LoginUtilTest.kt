package com.example.covisocials

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginUtilTest {

    @Test
    fun emptyUsernameReturnsFalse(){
        var result = LoginUtil.loginInput(
            "",
            "123456"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun lessNumberOfCharacters(){
        var result = LoginUtil.loginInput(
            "pratik12@gmail.com",
            "123456"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `special_Char_Not_Allowed`(){
        var result = LoginUtil.loginInput(
            "pratik12@gmail.com",
            "123345@"
        )
        assertThat(result).isFalse()
    }

}