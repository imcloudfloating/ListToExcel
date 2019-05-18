package cloud.list2excel.util

import cloud.list2excel.annotation.Header
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

/**
 * List 导出 Excel 表格
 * @author Cloud
 */
class ListToExcel(private val list: List<Any>) {

    private val workbook = HSSFWorkbook()
    private val sheet = workbook.createSheet()

    fun toExcel(): HSSFWorkbook {
        if (list.isEmpty()) {
            return workbook
        }

        val headers: MutableList<String> = ArrayList()
        val data: MutableList<MutableList<Any>> = ArrayList()

        // 获取注解并设置表头
        for (prop in list[0].javaClass.kotlin.memberProperties) {
            prop.isAccessible = true

            prop.annotations.forEach {
                if (it is Header) {
                    headers.add(it.name)
                }
            }
        }

        // 获取数据
        for (obj in list) {
            val rowData: MutableList<Any> = ArrayList()
            for (prop in obj.javaClass.kotlin.memberProperties) {
                prop.isAccessible = true

                prop.annotations.forEach {
                    if (it is Header) {
                        val t = prop.get(obj)
                        if (t == null) {
                            rowData.add("")
                        } else {
                            rowData.add(t)
                        }
                    }
                }
            }
            data.add(rowData)
        }

        setHeader(headers)
        setData(data)
        return workbook
    }

    /**
     * 设置表格头
     */
    private fun setHeader(headers: List<String>) {
        println(headers)

        val row = sheet.createRow(0)

        for (i in headers.indices) {
            val cell = row.createCell(i)
            cell.setCellValue(headers[i])
        }
    }

    /**
     * 写入数据
     */
    private fun setData(data: List<List<Any>>) {
        println(data)

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