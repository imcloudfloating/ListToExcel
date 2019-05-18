package cloud.list2excel.util

import org.junit.Before
import java.io.File
import java.sql.Date

class ListToExcelTest {

    private val films: MutableList<Film> = ArrayList()

    @Before
    fun generateData() {
        films.run {
            add(Film(1, "Iron Man", Date.valueOf("2008-4-30"), "None"))
            add(Film(2, "Iron Man 2", Date.valueOf("2010-5-7"), "None"))
            add(Film(3, "Iron Man 3", Date.valueOf("2013-5-1"), "None"))
        }
    }

    @org.junit.Test
    fun toExcel() {
        val before = System.currentTimeMillis()
        val workbook = ListToExcel(films).toExcel()
        val after = System.currentTimeMillis()
        println("所有操作: ${after - before}ms")
        workbook.write(File("F:/Desktop/films.xls"))
    }
}