package ru.simbirdevs.task_parthnerkin.data.retrofit


import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInt {
    @GET("/api_ios_test/list?api_key=DMwdj29q@S29shslok2")
    fun getConferences(): Call<Root>
}
