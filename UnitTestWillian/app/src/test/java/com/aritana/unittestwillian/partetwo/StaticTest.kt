package com.aritana.unittestwillian.partetwo

import android.text.TextUtils
import com.aritana.unittestwillian.string.StringValidator
import com.aritana.unittestwillian.string.isValdPassword
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.Assert.assertTrue
import org.junit.Test

class StaticTest {

    @Test
    fun `static behavior`() {
        mockkStatic("com.aritana.unittestwillian.string.StringExtensionsKt")

        every { "1".isValdPassword() } returns true

        val result = "1".isValdPassword()

        assertTrue(result)

        unmockkStatic("com.aritana.unittestwillian.string.StringExtensionsKt")
    }


    @Test
    fun `static behavior 2`() {
        mockkStatic(TextUtils::class)

        val stringValidator = StringValidator()

        every { TextUtils.isEmpty("") } returns true

        val result = stringValidator.isEmpty("")

        assertTrue(result)

        unmockkStatic(TextUtils::class)
    }

}