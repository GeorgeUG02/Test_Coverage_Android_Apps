package geekbrains.ru.translator.model.data

import com.google.gson.annotations.SerializedName

class DataModel(
    @field:SerializedName("word") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)
