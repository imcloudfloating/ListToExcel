package cloud.list2excel.util

import java.io.File
import java.sql.Date

class ListToExcelTest {

    private val films = listOf(
        Film(1, "Iron Man", Date.valueOf("2008-4-30"), "126 min"),
        Film(2, "Star Wars: Episode IV - A New Hope", Date.valueOf("1977-5-25"), "121 min"),
        Film(3, "Zootropolis", Date.valueOf("2016-3-4"), "109 min")
    )

    private val actors = listOf(
        Actor(1, "Robert John Downey Jr.", Date.valueOf("1965-4-4")),
        Actor(2, "Mark Hamill", Date.valueOf("1951-9-25")),
        Actor(3, "Ginnifer Goodwin", Date.valueOf("1978-5-22"))
    )

    private val data = mapOf(
        Pair("films", films),
        Pair("actors", actors)
    )

    @org.junit.Test
    fun toExcel() {
        val before = System.currentTimeMillis()
        val workbook = ListToExcel.from(data)
        val after = System.currentTimeMillis()
        println("Time Usage: ${after - before}ms")
        workbook.write(File("D:/Desktop/data.xls"))
    }
}