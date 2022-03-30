package com.raywenderlich.android.potterverse

class PotterTestApp : PotterApp() {

    var url = "http://127.0.0.1:8080"

    /*
        Here, http://127.0.0.1 is the local URL of your computer
        and 8080 is the port MockWebServer will use.
     */
    override fun getBaseUrl(): String {
        return url
    }
}