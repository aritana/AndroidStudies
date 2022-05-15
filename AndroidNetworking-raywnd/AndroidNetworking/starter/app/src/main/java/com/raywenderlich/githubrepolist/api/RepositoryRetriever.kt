package com.raywenderlich.githubrepolist.api

// Other imported classes
import com.raywenderlich.githubrepolist.data.RepoResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryRetriever {
    private val service: GithubService

    companion object {
        //1
        const val BASE_URL = "https://api.github.com/"
    }

    init {
        // 2
        val retrofit = Retrofit.Builder()
            // 1
            .baseUrl(BASE_URL)
            //3
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //4
        service = retrofit.create(GithubService::class.java)
    }

    suspend fun getRepositories(): RepoResult  {
        return service.searchRepositories()
    }

}
