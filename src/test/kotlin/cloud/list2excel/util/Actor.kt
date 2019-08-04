package cloud.list2excel.util

import cloud.list2excel.annotation.Header
import java.sql.Date

data class Actor(
    @Header(title = "ID")
    var id: Int? = null,

    @Header(title = "Full Name")
    var name: String? = null,

    @Header(title = "Birth")
    var birth: Date?=null
)