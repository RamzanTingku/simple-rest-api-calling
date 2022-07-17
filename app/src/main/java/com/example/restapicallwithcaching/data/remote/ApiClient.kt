package com.example.restapicallwithcaching.data.remote
import com.example.restapicallwithcaching.utils.const.UrlConst
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private var baseGitHubUrl = UrlConst.github_base_url

    private var retrofit: Retrofit? = null

    fun retrofit(baseUrl: String = baseGitHubUrl): Retrofit  {
        if(retrofit == null){
            retrofit =  createRetrofit(baseUrl)
        }
        return retrofit!!
    }

    private  fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }


    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    private fun getOkHttpClient(): OkHttpClient {
        val  loggingInterceptor = getLoggingInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
