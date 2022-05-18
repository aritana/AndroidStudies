package com.aritana.unittestwillian.fetch

class RepositoryHeroesImpl : RepositoryHeroes {
    override fun getHeroes(): List<String> {

        return listOf("Miranha", "Batema", "Ant man")
    }
}