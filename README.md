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
val films = listOf(
    Film(1, "Iron Man", Date.valueOf("2008-4-30"), "Iron Man is a 2008 American superhero film."),
    Film(2, "Iron Man 2", Date.valueOf("2010-5-7"), "Iron Man 2 is a 2010 American superhero film."),
    Film(3, "Iron Man 3", Date.valueOf("2013-5-1"), "Iron Man 3 is a 2013 American superhero film")
)
    
val workbook = ListToExcel(films).toExcel()
workbook.write(File("/home/films.xls"))
```

### 导出结果

ID	|标题	    |上映时间	|简介
----|-----------|-----------|---------
1	|Iron Man	|2008-04-30	|Iron Man is a 2008 American superhero film.
2	|Iron Man 2	|2010-05-07	|Iron Man 2 is a 2010 American superhero film.
3	|Iron Man 3	|2013-05-01	|Iron Man 3 is a 2013 American superhero film

