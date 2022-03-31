package com.example.handsonweek3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.example.handsonweek3.DetailsActivity.Companion.EXTRA_ANIME_ID
import com.example.handsonweek3.adapters.AnimeResultAdapter
import com.example.handsonweek3.services.SearchAnimeResult
import com.example.handsonweek3.services.SearchService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    // private lateinit var searchService: SearchService
    private lateinit var searchService: SearchService

    private lateinit var recyclerViewAdapter: AnimeResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        prepareView()
        searchService = buildSearchService()
    }

    private fun prepareView() {
        // Creating recycler view adapter with an empty list.
        recyclerViewAdapter = AnimeResultAdapter(emptyList()) { navigateToAnimeDetails(it) }

        findViewById<RecyclerView>(R.id.recyclerView).adapter = recyclerViewAdapter

        // Adding click listener to search button.
        findViewById<Button>(R.id.searchButton).setOnClickListener { dispatchRequestAnimes() }
    }

    private fun dispatchRequestAnimes() {
        // TODO: Request Animes list in the correct coroutine scope to update the UI
        val mainActivityJob: CompletableJob = Job()
        val coroutineScope: CoroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)

        coroutineScope.launch {
            requestAnimes()
        }
    }

   private suspend fun  requestAnimes() {
        val text = findViewById<AppCompatEditText>(R.id.searchText).text.toString()//id

        //TODO: Consume the API passing the search query and wait for the result.
        // Success: recyclerViewAdapter.updateItems(foundAnimes.data.results)
        // Error: recyclerViewAdapter.updateItems(emptyList()) and display a Toast with a message
        // Tip: Take a look at the returns from toRequestResult() (Not mandatory)


            var foundAnimes = searchService
                .search(text)

            //TODO: PEDIR AJUDA PARA IMPLEMENTAR TIP
            recyclerViewAdapter.updateItems(foundAnimes.results)
            recyclerViewAdapter.notifyDataSetChanged()


        //TODO:Apagar código antigo abaixo:

 /*       searchService.search(text)
            .enqueue(object : retrofit2.Callback<SearchResult> {
                override fun onResponse(
                    call: retrofit2.Call<SearchResult>,
                    response: retrofit2.Response<SearchResult>
                ) {
                    val foundAnimes = response.body()
                    if (foundAnimes != null) {
                        recyclerViewAdapter.updateItems(foundAnimes.results)
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: retrofit2.Call<SearchResult>, t: Throwable) {
                    recyclerViewAdapter.updateItems(emptyList())

                    val text = "Failure in load data!"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(applicationContext, text, duration)
                    toast.show()

                }

            })*/


}

//private fun buildSearchService(): SearchService {
private fun buildSearchService(): SearchService {
    // Create the moshi instance to help on parsing the results to local objects.
    val moshi = Moshi.Builder().build()

    val okHttpClient = OkHttpClient().newBuilder()
        .build()

    // Create the retrofit instance defining the API base URl and configuring moshi on it.
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.jikan.moe/v3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // Generate the needed service instance to consume.
    return retrofit.create(SearchService::class.java)
}

private fun navigateToAnimeDetails(anime: SearchAnimeResult) {
    val intent = Intent(this, DetailsActivity::class.java)
    intent.putExtra(EXTRA_ANIME_ID, anime.id)

    startActivity(intent)
}
}