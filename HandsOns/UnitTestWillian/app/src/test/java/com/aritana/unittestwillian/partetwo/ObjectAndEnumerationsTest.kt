package com.aritana.unittestwillian.partetwo

import com.aritana.unittestwillian.calculator.CalculatorObject
import com.aritana.unittestwillian.calculator.Enumeration
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkObject
import org.junit.Assert.assertEquals
import org.junit.Test

class ObjectAndEnumerationsTest {

    @Test
    fun `when call sum should return sum value`() {

        val calculator = CalculatorObject

        mockkObject(calculator)

        every { calculator.sum(10, 20) } returns 50

        val result = calculator.sum(10, 20)

        assertEquals(50, result)

        unmockkObject(calculator)

        val result2 = calculator.sum(10, 20)

        assertEquals(30, result2)
    }

    @Test
    fun `when call enumeration should return value`() {
        val enumeration = Enumeration.CONSTANT
        mockkObject(enumeration)
        every { enumeration.value } returns 100

        val result = enumeration.value

        assertEquals(100, result)

        unmockkObject(enumeration)
    }
}