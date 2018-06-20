package diandian.myapplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by funplus on 2018/6/15.
 */

@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface PointCutAnnotation {

    public String MethodName();
    public String clazz();
    public String params();

}
