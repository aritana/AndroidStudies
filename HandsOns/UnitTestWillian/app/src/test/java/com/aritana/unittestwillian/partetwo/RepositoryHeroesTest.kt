package com.aritana.unittestwillian.partetwo

import com.aritana.unittestwillian.fetch.RepositoryHeroesImpl
import org.junit.Assert
import org.junit.Test

class RepositoryHeroesTest {

    @Test
    fun `when call getHeroes should return heroes`() {

        val repository = RepositoryHeroesImpl()

        val result = repository.getHeroes()

        Assert.assertEquals(listOf("Miranha", "Batema", "Ant man"), result)
    }



}