package io.seata.samples.mutiple.mybatisplus.config.annotation;



import io.seata.samples.mutiple.mybatisplus.config.DataSourceKey;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DB
{
    /**
     * 切换数据源名称
     */
    DataSourceKey value() default DataSourceKey.PAY;
}
