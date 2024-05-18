package almerti.egline.data.source.network.model


data class Book(
    val id : Int,
    val title : String,
    val description : String,
    val cover : ByteArray,
    val rating : Double,
    val year : Int,
    val views : Int,
    val status : String,
    val genres : List<String>,
    val rates : Int
)

