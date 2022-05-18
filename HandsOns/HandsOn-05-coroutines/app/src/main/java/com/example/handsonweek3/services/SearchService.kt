package com.example.handsonweek3.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    // TODO: Convert it to a suspend function: DONE
    @GET("search/anime")
  suspend  fun search(@Query(value = "q") search: String):SearchResult
}