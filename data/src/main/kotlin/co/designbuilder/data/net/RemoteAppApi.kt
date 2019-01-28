package co.designbuilder.data.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteAppApi : AppApi {

    private var client: AppApi

    init {
        val httpClient = OkHttpClient.Builder()

        val builder = Retrofit.Builder()
                .baseUrl(Backend.instance.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder
                .client(httpClient.build())
                .build()

        client = retrofit.create(AppApi::class.java)
    }

    override fun fetchWords(wordRequest: WordRequest) = client.fetchWords(wordRequest)
}
