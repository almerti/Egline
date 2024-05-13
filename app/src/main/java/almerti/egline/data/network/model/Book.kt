package almerti.egline.data.network.model


data class Book(
    val id : Int ,
    val title : String ,
    val description : String ,
    val cover : ByteArray ,
    val rating : Double ,
//    val ratingCount : Int ,
    val year : String ,
    val views : Int ,
    val status : String ,
    val genre : List<Genre> ,
    val rates : Int
)

