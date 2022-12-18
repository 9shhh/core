package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // TYPE => Class 레벨을 의미함.
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
