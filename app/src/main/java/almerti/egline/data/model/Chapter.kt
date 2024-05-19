package almerti.egline.data.model

import java.time.LocalDate

data class Chapter(
    val id : Int,
    val bookId : Int,
    val title : String,
    val number : Int,
    var textContent : String,
    val date : LocalDate
)
