package com.gavin.rxbus.annotation.accept;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Gavin
 * E-mail: GavinChangCN@163.com
 * Desc:
 * Date: 2016-06-14
 * Time: 11:19
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Accept {
    AcceptScheduler acceptScheduler() default AcceptScheduler.MAIN_THREAD;

    AcceptType[] value() default {};
}