package cloud.list2excel.util

import org.junit.Before
import java.io.File
import java.sql.Date

class ListToExcelTest {

    private val films: MutableList<Film> = ArrayList()

    @Before
    fun generateData() {
        for (i in 0 until 100) {
            films.add(
                Film(
                    i,
                    "Film Title $i",
                    Date.valueOf("2019-5-18"),
                    "Description of Film Title $i"
                )
            )
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