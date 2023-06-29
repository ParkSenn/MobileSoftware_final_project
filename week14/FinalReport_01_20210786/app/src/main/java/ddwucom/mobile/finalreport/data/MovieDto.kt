package ddwucom.mobile.finalreport.data

data class MovieDto(val id: Int, var poster: Int, var title: String,
                    var director: String, var actor: String, var rate: Double?, var story: String): java.io.Serializable {

}