package ru.ivos.megamarketopenapi.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author iVos 22.06.2022
 */

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= CheckPriceTypeValidatorImpl.class)
public @interface CheckPriceTypeValidator{
    String message() default "Insert data isn't valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
