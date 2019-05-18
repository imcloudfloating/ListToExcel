package cloud.list2excel.util

import java.io.File
import java.sql.Date

class ListToExcelTest {

    private val films = listOf(
        Film(1, "Iron Man", Date.valueOf("2008-4-30"), "Iron Man is a 2008 American superhero film."),
        Film(2, "Iron Man 2", Date.valueOf("2010-5-7"), "Iron Man 2 is a 2010 American superhero film."),
        Film(3, "Iron Man 3", Date.valueOf("2013-5-1"), "Iron Man 3 is a 2013 American superhero film")
    )

    @org.junit.Test
    fun toExcel() {
        val before = System.currentTimeMillis()
        val workbook = ListToExcel(films).toExcel()
        val after = System.currentTimeMillis()
        println("Time Usage: ${after - before}ms")
        workbook.write(File("F:/Desktop/films.xls"))
    }
}