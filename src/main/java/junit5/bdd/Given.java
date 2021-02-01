package junit5.bdd;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Givens.class)
public @interface Given {
    String[] value();
}
