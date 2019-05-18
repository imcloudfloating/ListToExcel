# List 导出为 Excel 表格

## 使用 @Header 注解

```kotlin
data class Film(
    @Header("ID")
    private var id: Int? = null,

    @Header("标题")
    var title: String? = null,

    @Header("上映时间")
    var release_date: Date? = null,

    @Header("简介")
    var description: String? = null
)
```

## 导出到表格

```kotlin
val films: List<Film> = ...
val workbook = ListToExcel(list).toExcel()
workbook.write(File("/home/films.xls"))
```
