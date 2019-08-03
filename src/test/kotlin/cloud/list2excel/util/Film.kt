package cloud.list2excel.util

import cloud.list2excel.annotation.Header
import java.sql.Date

data class Film(
    @Header("ID")
    var id: Int? = null,

    @Header("Title")
    var title: String? = null,

    @Header("Release Date")
    var release_date: Date? = null,

    @Header("Duration")
    var duration: String? = null
)