package cloud.list2excel.annotation;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    String title() default "";
    short fontSize() default 14;
    HSSFColor.HSSFColorPredefined fontColor() default HSSFColor.HSSFColorPredefined.BLACK;
    HSSFColor.HSSFColorPredefined borderColor() default HSSFColor.HSSFColorPredefined.BLACK;
}