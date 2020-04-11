package io.seata.samples.mutiple.mybatisplus.config.annotation.aspectj;

import io.seata.samples.mutiple.mybatisplus.config.DynamicDataSourceContextHolder;
import io.seata.samples.mutiple.mybatisplus.config.annotation.DB;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 *   动态数据源Aop切换
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/12/31
 **/
@Aspect
@Order(1)//配置加载顺序
@Component
public class DataSourceAspect {

    /**
     * 注解在类（@within），或者方法（@annotation）都可以被 拦截
     */
    @Pointcut("@annotation(io.seata.samples.mutiple.mybatisplus.config.annotation.DB)" +
            " || @within(io.seata.samples.mutiple.mybatisplus.config.annotation.DB)")
    public void doPointCut(){}


    @Around("doPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DB dataSource = getDataSource(point);
        if (!Objects.isNull(dataSource)){
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
        }

        try {
            return point.proceed();
        }
        finally {
            // 在执行方法之后 销毁数据源
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }

    }



    /**
     * 获取@DB注解
     */
    public DB getDataSource(ProceedingJoinPoint point){

        //获得当前访问的class
        Class<? extends Object> className = point.getTarget().getClass();

        // 判断是否存在@DateBase注解
        if (className.isAnnotationPresent(DB.class)) {
            //获取注解
            DB targetDataSource = className.getAnnotation(DB.class);
            return targetDataSource;
        }

        Method method = ((MethodSignature)point.getSignature()).getMethod();
        DB dataSource = method.getAnnotation(DB.class);
        return dataSource;

    }
}
