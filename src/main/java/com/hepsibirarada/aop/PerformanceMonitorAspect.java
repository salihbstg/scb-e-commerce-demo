package com.hepsibirarada.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class PerformanceMonitorAspect {

    private final Logger LOGGER=LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    @Around("execution(* com.hepsibirarada.service.*.*(..))")
    public Object performanceMonitor(ProceedingJoinPoint jp) throws Throwable {
        long start=System.currentTimeMillis();
        Object obj=jp.proceed();
        long end=System.currentTimeMillis();
        LOGGER.info("Time taken by: "+jp.getSignature().getName()+" "+(end-start)+" ms");
        return obj;
    }

}
