package com.aritana.unittestwillian.partetwo

import com.aritana.unittestwillian.foobar.FooBar
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FooBarTest {

    @MockK
    private  lateinit var fooBar: FooBar

    @RelaxedMockK
    private  lateinit var  fooBarRelaxed:FooBar

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `when call getSomething should return message`() {


        every { fooBar.getSomething() } returns "Mad World"

        val result = fooBar.getSomething()

        assertEquals("Mad World",result)
    }

    @Test
    fun `when call getSomething with relaxed should return default value`() {

        val result = fooBarRelaxed.getSomething()
        assertEquals("",result)
    }


    @Test
    fun `test with spy`() {
        val fooBarSpy = spyk<FooBar>()

        every { fooBarSpy.getSomething() } returns "message mocked"

        val something = fooBarSpy.getSomething()

        val realSomething = fooBarSpy.getRealSomething()

        assertEquals("message mocked", something)
        assertEquals("Real Hello World", realSomething)
    }


    }