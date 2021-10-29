package geekbrains.ru.translator.model.datasource

import geekbrains.ru.translator.model.data.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/v2/entries/en/{word}")
    fun search(@Path("word") wordToSearch: String): Observable<List<DataModel>>
}
