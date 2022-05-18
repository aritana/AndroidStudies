package com.aritana.unittestwillian.partetwo

import com.aritana.unittestwillian.fetch.FetchHeroesImpl
import com.aritana.unittestwillian.fetch.RepositoryHeroes
import com.aritana.unittestwillian.fetch.ViewStatus
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FetchHeroesTest {

    @MockK
    private lateinit var repositoryHeroes:RepositoryHeroes
    @MockK
    private lateinit var viewStatus:ViewStatus

    @InjectMockKs
    private lateinit var fetchHeroesImpl: FetchHeroesImpl

    @Before
    fun setUpt(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `given a fetch when called then should return heroes`() {


        every { viewStatus.showLoading() } just runs
        every { repositoryHeroes.getHeroes() } returns listOf("Miranha", "Batema", "Ant man")
        every { viewStatus.hiddenLoading() } just runs

        var result =  fetchHeroesImpl.fetch()

        Assert.assertEquals(listOf("Miranha", "Batema", "Ant man"),result)

        verifyAll {

            viewStatus.showLoading()
            repositoryHeroes.getHeroes()
            viewStatus.hiddenLoading()
        }

        verifySequence {

            viewStatus.showLoading()
            repositoryHeroes.getHeroes()
            viewStatus.hiddenLoading()

        }

        verify(exactly = 1){
            viewStatus.showLoading()
            repositoryHeroes.getHeroes()
            viewStatus.hiddenLoading()
        }

        confirmVerified(repositoryHeroes,viewStatus)
    }
}