package com.demo.config.rest;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 仅在ResponseBodyAdvice 指定的注解下有效。
 */
@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PackageResponseBody {
    /**
     * 是否用RestResponse包装
     * @return
     */
    boolean value() default true;
}