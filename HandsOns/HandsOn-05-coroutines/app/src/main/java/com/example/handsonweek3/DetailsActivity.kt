package com.example.handsonweek3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.handsonweek3.adapters.AnimeCharacterResultAdapter
import com.example.handsonweek3.services.AnimeCharacterResult
import com.example.handsonweek3.services.AnimeResult
import com.example.handsonweek3.services.AnimeService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStream
import java.net.URL

class DetailsActivity : AppCompatActivity() {

    private lateinit var animeService: AnimeService

    private lateinit var charactersRecyclerViewAdapter: AnimeCharacterResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)
        prepareView()

        animeService = buildAnimeService()

        // TODO: Request Animes details in the correct coroutine scope to update the UI: DONE
        val mainActivityJob: CompletableJob = Job()
        val coroutineScope: CoroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)

        coroutineScope.launch {
            requestAnimeDetails()
        }
    }

    private fun prepareView() {
        title = ""

        // Creating recycler view adapter with an empty list.
        charactersRecyclerViewAdapter = AnimeCharacterResultAdapter(emptyList())
        with(findViewById<RecyclerView>(R.id.charactersRecyclerView)) {
            adapter = charactersRecyclerViewAdapter
            isNestedScrollingEnabled = false
        }
    }

    private suspend fun requestAnimeDetails() {
        val animeId = intent.getIntExtra(EXTRA_ANIME_ID, 0)

        //TODO: Consume the API passing the anime id and wait for the result.
        // Success: prepareAnimeDetailsView(result.data); requestAnimeCharacters(animeId)
        // Error: Display a Toast with a message
        // Tip: Take a look at the returns from toRequestResult() (Not mandatory)
        // Get the anime details to fill the screen.

        var animeResult = animeService
            .getDetails(animeId)

        prepareAnimeDetailsView(animeResult);
        requestAnimeCharacters(animeId)

        /*  .enqueue(object : retrofit2.Callback<AnimeResult> {
              override fun onResponse(call: Call<AnimeResult>, response: Response<AnimeResult>) {
                  val animeResult = response.body()
                  if (animeResult != null) {
                      prepareAnimeDetailsView(animeResult);
                      requestAnimeCharacters(animeId)
                  }
              }

              override fun onFailure(call: Call<AnimeResult>, t: Throwable) {
                  val text = "Failure in load data!"
                  val duration = Toast.LENGTH_SHORT
                  val toast = Toast.makeText(applicationContext, text, duration)
                  toast.show()
              }
          })*/
    }

    private suspend fun requestAnimeCharacters(animeId: Int) {
        //TODO: Consume the API passing the anime id and wait for the result.
        // Success: prepareAnimeCharactersRecyclerView(result.data.characters)
        // Error: Display a Toast with a message
        // Tip: Take a look at the returns from toRequestResult() (Not mandatory)
        // Get the anime characters and parse the result.

        var animeCharacterResult = animeService
            .getCharactersAndStaff(animeId)

        prepareAnimeCharactersRecyclerView(animeCharacterResult.characters)

        //TODO:Apagar c??digo antigo abaixo:

        /*      .enqueue(object : retrofit2.Callback<AnimeCharactersStaffResult> {
                  override fun onResponse(
                      call: Call<AnimeCharactersStaffResult>,
                      response: Response<AnimeCharactersStaffResult>
                  ) {
                      val animeCharacterResult = response.body()
                      if (animeCharacterResult != null) {
                          prepareAnimeCharactersRecyclerView(animeCharacterResult.characters)
                      }
                  }

                  override fun onFailure(call: Call<AnimeCharactersStaffResult>, t: Throwable) {
                      val text = "Failure in load data!"
                      val duration = Toast.LENGTH_SHORT
                      val toast = Toast.makeText(applicationContext, text, duration)
                      toast.show()
                  }
              })*/
    }

    private fun prepareAnimeDetailsView(value: AnimeResult) {
        // TODO: Remove AsyncTask call and use a coroutine to fetch the image in the correct Scope: Done

        val mainActivityJob: CompletableJob = Job()
        val coroutineScope: CoroutineScope = CoroutineScope(mainActivityJob + Dispatchers.IO)

        coroutineScope.launch {
            // Downloading the bitmap inside the task.
            val bitmap = BitmapFactory.decodeStream(URL(value.imageUrl).content as InputStream)

            // Setting the downloaded image to the ImageView.
            with(findViewById<ImageView>(R.id.bannerImage)) { post { setImageBitmap(bitmap) } }

        }
     /*   doAsync {

            // Downloading the bitmap inside the task.
            val bitmap = BitmapFactory.decodeStream(URL(value.imageUrl).content as InputStream)

            // Setting the downloaded image to the ImageView.
            with(findViewById<ImageView>(R.id.bannerImage)) { post { setImageBitmap(bitmap) } }

        }*/

        title = value.title

        findViewById<TextView>(R.id.name).text = value.title

        findViewById<View>(R.id.animeTitleSeparator).visibility = VISIBLE

        findViewById<TextView>(R.id.animeInfo).text =
            "Episodes: ${value.episodes} | Score: ${value.score} | Airing: ${value.airing.toYesNo()}"

        findViewById<TextView>(R.id.description).text = value.synopsis
    }

    private fun prepareAnimeCharactersRecyclerView(value: List<AnimeCharacterResult>) {
        findViewById<View>(R.id.charactersTitle).visibility = VISIBLE
        findViewById<View>(R.id.charactersTitleSeparator).visibility = VISIBLE

        charactersRecyclerViewAdapter.updateItems(value)
        charactersRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun Boolean.toYesNo() = if (this) "Yes" else "No"

    private fun buildAnimeService(): AnimeService {
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
        return retrofit.create(AnimeService::class.java)
    }

    companion object {
        const val EXTRA_ANIME_ID = "EXTRA_ANIME_ID"
    }
}