package com.lifescan.network.loadList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import com.lifescan.network.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadListActivity : AppCompatActivity() {

    private fun getListView(): ListView = findViewById(R.id.loadListListView)
    private fun getButton(): TextView = findViewById(R.id.loadListButton)
    private fun getTextView(): TextView = findViewById(R.id.loadListTextView)
    private fun getProgressBar(): ProgressBar = findViewById(R.id.loadListProgressBar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_list)
        getButton().setOnClickListener { requestSuperHeroes() }
        loadingState(false)
    }

    private fun requestSuperHeroes() {
        loadingState(true)
        LoadListRetrofit().retrofitService.getSuperHeroes().let { call ->
            call.enqueue(object : Callback<List<LoadListResult>> {
                override fun onResponse(call: Call<List<LoadListResult>>, response: Response<List<LoadListResult>>) {
                    val myHeroList: List<LoadListResult>? = response.body()
                    myHeroList?.let {
                        val listHeroes = arrayOfNulls<String>(myHeroList.size)
                        for (i in myHeroList.indices) {
                            listHeroes[i] = "${myHeroList[i].id} - ${myHeroList[i].name}"
                        }
                        getListView().adapter =
                            ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listHeroes)
                    }
                    loadingState(false)
                }

                override fun onFailure(call: Call<List<LoadListResult>>, t: Throwable?) {
                    loadingState(false)
                }
            })
        }
    }

    private fun loadingState(isLoading: Boolean) {
        getButton().isEnabled = !isLoading
        getListView().isVisible = !isLoading
        getTextView().isVisible = isLoading
        getProgressBar().isVisible = isLoading
    }

}