# List 导出为 Excel 表格

支持导出多个工作表。

## 使用 @Header 注解

只使用 `@Header` 注解不提供值，则使用字段名称作为表格头。

```kotlin
data class Film(
    @Header("ID")
    private var id: Int? = null,

    @Header("Title")
    var title: String? = null,

    @Header("Release Date")
    var release_date: Date? = null,

    @Header("Duration")
    var duration: String? = null
)
```

## 导出到表格

### 单个工作表（参数为 List）
```kotlin
val films = listOf(
    Film(1, "Iron Man", Date.valueOf("2008-4-30"), "126 min"),
    Film(2, "Star Wars: Episode IV - A New Hope", Date.valueOf("1977-5-25"), "121 min"),
    Film(3, "Zootropolis", Date.valueOf("2016-3-4"), "109 min")
)
    
val workbook = ListToExcel.toExcel(films)
workbook.write(File("/home/films.xls"))
```

### 多个工作表（参数为 Map）

```kotlin
val films = listOf(
    Film(1, "Iron Man", Date.valueOf("2008-4-30"), "126 min"),
    Film(2, "Star Wars: Episode IV - A New Hope", Date.valueOf("1977-5-25"), "121 min"),
    Film(3, "Zootropolis", Date.valueOf("2016-3-4"), "109 min")
)

val actors = listOf(
    Actor(1, "Robert John Downey Jr.", Date.valueOf("1965-4-4")),
    Actor(2, "Mark Hamill", Date.valueOf("1951-9-25")),
    Actor(3, "Ginnifer Goodwin", Date.valueOf("1978-5-22"))
)

val data = mapOf(
    Pair("films", films),
    Pair("actors", actors)
)

val workbook = ListToExcel.toExcel(data)
workbook.write(File("/home/data.xls"))
```

