package com.aritana.unittestwillian.fetch

class FetchHeroesImpl(
    private val repositoryHeroes: RepositoryHeroes,
    private val viewStatus: ViewStatus
) : FetchHeroes {
    override fun fetch( ): List<String> {

        viewStatus.showLoading()
        val result = repositoryHeroes.getHeroes()
        viewStatus.hiddenLoading()

        return result
    }
}