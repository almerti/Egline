package almerti.egline.data.network.model


data class Chapter(
    val id: Int ,
    val bookId: Int ,
    val name: String ,
    val number : Int ,
    val textContent: String ,
    val audioContent: String ,
    val date: Long
)
