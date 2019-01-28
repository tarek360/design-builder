package co.designbuilder.data.net

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AppApi {

    @POST("words")
    fun fetchWords(@Body wordRequest: WordRequest): Observable<WordResponse>
}
