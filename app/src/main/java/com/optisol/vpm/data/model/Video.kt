package com.optisol.vpm.data.model

data class VideoResponse (

     var page       : Int?            = null,
     var per_page    : Int?            = null,
     var total      : Int?            = null,
     var total_pages : Int?            = null,
     var data       : ArrayList<Video> = arrayListOf(),
     var support    : Support?        = Support()

)

data class Video (

    var id        : Int?    = null,
    var email     : String? = null,
     var first_name : String? = null,
     var last_name  : String? = null,
    var avatar    : String? = null


)

data class Support (

    var url  : String? = null,
    var text : String? = null

)