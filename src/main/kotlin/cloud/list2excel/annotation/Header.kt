package cloud.list2excel.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class Header(val name: String)