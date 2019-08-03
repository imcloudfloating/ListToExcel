package cloud.list2excel.util

import cloud.list2excel.annotation.Header
import java.sql.Date

data class Actor(
    @Header("ID")
    var id: Int? = null,

    @Header("Full Name")
    var name: String? = null,

    @Header("Birth")
    var birth: Date?=null
)