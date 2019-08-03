package cloud.list2excel.util

import cloud.list2excel.annotation.Header
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import java.sql.Date

/**
 * List 导出 Excel 表格
 * @author Cloud
 */
object ListToExcel {

    private val workbook = HSSFWorkbook()

    /**
     * 处理单个 Sheet
     */
    fun toExcel(data: List<Any>): HSSFWorkbook {
        toExcel("Sheet0", data)
        return workbook
    }

    /**
     * 处理多个 Sheet
     */
    fun toExcel(data: Map<String, List<Any>>): HSSFWorkbook {
        if (data.isEmpty())
            return workbook

        for (sheet in data) {
            toExcel(sheet.key, sheet.value)
        }

        return workbook
    }

    private fun toExcel(sheetName: String, list: List<Any>) {
        val sheet = workbook.createSheet(sheetName)

        if (list.isEmpty())
            return

        val headers: MutableList<String> = ArrayList()
        val data: MutableList<MutableList<Any>> = ArrayList()

        // 获取注解并设置表头
        for (field in list[0].javaClass.declaredFields) {
            field.isAccessible = true

            val annotation = field.getAnnotation(Header::class.java)

            if (annotation != null) {
                headers.add(if (annotation.value == "") field.name else annotation.value)
            }
        }

        // 获取数据
        for (obj in list) {
            val rowData: MutableList<Any> = ArrayList()
            for (field in obj.javaClass.declaredFields) {
                field.isAccessible = true

                val annotation = field.getAnnotation(Header::class.java)

                if (annotation != null) {
                    val t = field.get(obj)
                    if (t == null) {
                        rowData.add("")
                    } else {
                        rowData.add(t)
                    }
                }
            }
            data.add(rowData)
        }

        setHeader(sheet, headers)
        setData(sheet, data)
    }

    /**
     * 设置表格头
     */
    private fun setHeader(sheet: HSSFSheet, headers: List<String>) {
        val row = sheet.createRow(0)

        for (i in headers.indices) {
            val cell = row.createCell(i)
            cell.setCellValue(headers[i])
        }
    }

    /**
     * 写入数据
     */
    private fun setData(sheet: HSSFSheet, data: List<List<Any>>) {
        for (i in data.indices) {
            val row = sheet.createRow(i + 1)

            for (j in data[i].indices) {
                val cell = row.createCell(j)
                cell.setCellValue(data[i][j].toString())
                sheet.autoSizeColumn(j)
            }
        }
    }
}