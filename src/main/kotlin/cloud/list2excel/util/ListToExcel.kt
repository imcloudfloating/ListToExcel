package cloud.list2excel.util

import cloud.list2excel.annotation.Column
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.BorderStyle

/**
 * List/Map 导出 Excel 表格
 * @author Cloud
 */
object ListToExcel {

    private var workbook: HSSFWorkbook = HSSFWorkbook()
    private val cellStyles: MutableList<HSSFCellStyle> = ArrayList()

    /**
     * 处理单个 Sheet
     */
    fun from(data: List<Any>): HSSFWorkbook {
        toWorkbook("Sheet0", data)
        return workbook
    }

    /**
     * 处理多个 Sheet
     */
    fun from(data: Map<String, List<Any>>): HSSFWorkbook {
        if (data.isEmpty())
            return workbook

        for (sheet in data) {
            toWorkbook(sheet.key, sheet.value)
        }

        return workbook
    }

    private fun toWorkbook(sheetName: String, list: List<Any>) {
        val sheet = workbook.createSheet(sheetName)

        if (list.isEmpty())
            return

        val headers: MutableList<String> = ArrayList()
        val data: MutableList<MutableList<Any>> = ArrayList()

        // 获取注解并设置表头
        for (field in list[0].javaClass.declaredFields) {
            field.isAccessible = true

            val annotation = field.getAnnotation(Column::class.java)

            if (annotation != null) {
                headers.add(if (annotation.title == "") field.name else annotation.title)

                val cellStyle = workbook.createCellStyle().also { style ->
                    style.setFont(workbook.createFont().also {
                        it.fontHeightInPoints = annotation.fontSize
                        it.color = annotation.fontColor.index
                    })

                }

                cellStyle.run {
                    leftBorderColor = annotation.borderColor.index
                    topBorderColor = annotation.borderColor.index
                    rightBorderColor = annotation.borderColor.index
                    bottomBorderColor = annotation.borderColor.index

                    borderLeft = BorderStyle.THIN
                    borderTop = BorderStyle.THIN
                    borderRight = BorderStyle.THIN
                    borderBottom = BorderStyle.THIN
                }

                cellStyles.add(cellStyle)
            }
        }

        // 获取数据
        for (obj in list) {
            val rowData: MutableList<Any> = ArrayList()
            for (field in obj.javaClass.declaredFields) {
                field.isAccessible = true

                val annotation = field.getAnnotation(Column::class.java)

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
            cell.setCellStyle(cellStyles[i])
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
                cell.setCellStyle(cellStyles[j])
                sheet.autoSizeColumn(j)
            }
        }
    }
}