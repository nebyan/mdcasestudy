package com.casestudy.repository

import com.casestudy.model.HomeData
import com.casestudy.network.ApiService
import com.casestudy.util.Result
import javax.inject.Inject

interface HomeRepository {

    suspend fun getHomeData(): Result<HomeData>

}

class HomeRepositoryImpl @Inject constructor(var apiService: ApiService): HomeRepository{

    override suspend fun getHomeData(): Result<HomeData> {

        val response = apiService.getHomeData()

        if (response.isSuccessful){
            return Result.Success(data = response.body())
        }

        return Result.Error(message = response.message())

    }

}