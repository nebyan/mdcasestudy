package com.casestudy.network

import com.casestudy.model.HomeData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("files/MobileInterviewProjectData.json")
    suspend fun getHomeData(): Response<HomeData>

    companion object {

        //TODO please provide real base url
        val BASE_URL = "https://mock.api.com/"

    }
}