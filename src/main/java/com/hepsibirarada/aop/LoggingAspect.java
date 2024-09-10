package com.hepsibirarada.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER= LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.hepsibirarada.service.*.*(..))")
    public void methodCall(JoinPoint jp){
        LOGGER.info("Method called! "+ jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.hepsibirarada.service.*.*(..))")
    public void logMethodExecutedSuccess(JoinPoint jp) {
        LOGGER.info("Method Executed Successfully "+jp.getSignature().getName()+"\n --------- ");
    }

    @AfterThrowing("execution(* com.hepsibirarada.service.*.*(..))")
    public void logMethodCrashed(JoinPoint jp) {
        LOGGER.info("Method has some issues "+jp.getSignature().getName()+"\n --------- ");
    }

}
