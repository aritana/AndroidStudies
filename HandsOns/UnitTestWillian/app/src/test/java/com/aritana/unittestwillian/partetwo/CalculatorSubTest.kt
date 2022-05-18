package com.aritana.unittestwillian.partetwo

import com.aritana.unittestwillian.calculator.CalculatorSut
import com.aritana.unittestwillian.calculator.DependencyOne
import com.aritana.unittestwillian.calculator.DependencyTwo
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CalculatorSubTest {

   @MockK
    private lateinit var dependencyOne: DependencyOne

   @MockK
    private lateinit var dependencyTwo: DependencyTwo

    @InjectMockKs
    private lateinit var calculator: CalculatorSut

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when call calculate should return sum value`() {

        val dependencyOne = mockk<DependencyOne>()
    //    val dependencyTwo = mockk<DependencyTwo>()

        every { dependencyOne.value } returns 5
        every { dependencyTwo.valueTwo } returns "10"

     //   val calculator = CalculatorSut(dependencyOne, dependencyTwo)

        val result = calculator.calculate()

        Assert.assertEquals(15, result)
    }
}