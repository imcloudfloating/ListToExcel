package cloud.list2excel.util

import cloud.list2excel.annotation.Column
import java.sql.Date

data class Actor(
    @Column(title = "ID")
    var id: Int? = null,

    @Column(title = "Full Name")
    var name: String? = null,

    @Column(title = "Birth")
    var birth: Date?=null
)