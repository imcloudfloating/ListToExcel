package cloud.list2excel.util

import cloud.list2excel.annotation.Column
import org.apache.poi.hssf.util.HSSFColor
import java.sql.Date

data class Film(
    @Column(title = "ID", fontColor = HSSFColor.HSSFColorPredefined.RED)
    var id: Int? = null,

    @Column(title = "Title")
    var title: String? = null,

    @Column(title = "Release Date")
    var release_date: Date? = null,

    @Column(title = "Duration")
    var duration: String? = null
)