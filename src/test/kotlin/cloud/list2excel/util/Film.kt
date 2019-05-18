package cloud.list2excel.util

import cloud.list2excel.annotation.Header
import java.sql.Date

data class Film(
    @Header("ID")
    private var id: Int? = null,

    @Header("标题")
    var title: String? = null,

    @Header("上映时间")
    var release_date: Date? = null,

    @Header("简介")
    var description: String? = null
)