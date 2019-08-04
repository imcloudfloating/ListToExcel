package cloud.list2excel.util

import cloud.list2excel.annotation.Header
import org.apache.poi.hssf.util.HSSFColor
import java.sql.Date

data class Film(
    @Header(title = "ID", fontColor = HSSFColor.HSSFColorPredefined.RED)
    var id: Int? = null,

    @Header(title = "Title")
    var title: String? = null,

    @Header(title = "Release Date")
    var release_date: Date? = null,

    @Header(title = "Duration")
    var duration: String? = null
)