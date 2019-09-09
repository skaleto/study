package annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : ybyao
 * @Create : 2019-08-06 15:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Order
public @interface CustomAnnotation {

}

