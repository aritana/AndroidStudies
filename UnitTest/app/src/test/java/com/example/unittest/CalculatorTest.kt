package com.example.unittest

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorTest {

    private lateinit var calculator: Calculator

    @MockK
    private lateinit var secretConversion:SecreteConversion

    @Before
    fun setUp() {

        MockKAnnotations.init(this)
        calculator = Calculator(secretConversion)
    }

    @Test
    fun addTwoNumbers() {

        val response = calculator.add(1, 3)
        assertEquals(response, 4)
    }

    @Test
    fun absoluteOfNegativeShouldBePositive() {

        val response = calculator.absolute(-1)
        assertEquals(1, response)
    }

    @Test
    fun `given my favorite number(7) and another number- then should return my favorite number  * another number`() {
        every { secretConversion.makeConversion(any()) }returns 1238F
        val response = calculator.myCrazyCalculation(7, -1)
        assertEquals(-7, response)
    }

    @Test
    fun `given not my favorite number(7) and another number- then should return 19`() {

        val response = calculator.myCrazyCalculation(8, -1)
        assertEquals(19, response)
    }

    //exercicio
    @Test
    fun `given two positive numbers (a,b) - then the power of (a,b) should be a^b`() {

        val response:Double = calculator.pow(2.0,3.0)
        assertEquals(8.0, response,0.001)
    }

    @Test
    fun `given two negative numbers (a,b) - then the power of (a,b) should be a^b`() {

        val response:Double = calculator.pow(-2.0,-3.0)
        assertEquals(-0.125, response,0.001)
    }


    @Test
    fun `given two numbers (a,b), if a equal 0 and b greater than 0 - then the power of (a,b) should be 0`() {

        val response:Double = calculator.pow(0.0,3.0)
        assertEquals(0.0, response,0.001)
    }

    @Test
    fun `given two numbers (a,b), if a equal 0 and b less than 0 - then the power of (a,b) should be INFINITY`() {

        val response:Double = calculator.pow(0.0,-3.0)
        assertEquals(Double.POSITIVE_INFINITY, response,0.001)
    }
    @Test
    fun `given two numbers (a,b), if b equal 0 - then the power of (a,b) should be 1`() {

        val response:Double = calculator.pow(9.0,0.0)
        assertEquals(1.0, response,0.001)
    }

    @Test
    fun `given two numbers (a,b), if a equal  - 0  and b any negative number - then the power of (a,b) should be -INFINITY`() {

        val response:Double = calculator.pow(-0.0,-3.0)
        assertEquals(Double.NEGATIVE_INFINITY, response,0.001)
    }
}