# ListToExcel
将 List 中的数据写入 Excel 表格

## 使用

```kotlin
val list = ...
val workbook = ListToExcel(list).toExcel()
workbook.write(...)
```
