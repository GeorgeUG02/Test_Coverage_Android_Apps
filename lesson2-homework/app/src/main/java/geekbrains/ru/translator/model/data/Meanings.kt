package geekbrains.ru.translator.model.data

import com.google.gson.annotations.SerializedName

class Meanings(
    @field:SerializedName("definitions") val definitions: List<Definition>?,
    @field:SerializedName("partOfSpeech") val partofSpeech: String?
)
