package org.micro.tcc.tc.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 *@author jeff.liu
 *   tcc 事务拦截器，主要拦截器
 * date 2019/7/31
 */
@Component
@Aspect
@Order(1)
@Slf4j
public  class TccTransactionAspect {

    @Autowired
    private TccTransactionInterceptor tccTransactionInterceptor;

    public void setTccTransactionInterceptor(TccTransactionInterceptor tccTransactionInterceptor) {
        this.tccTransactionInterceptor = tccTransactionInterceptor;
    }

    @Pointcut("@annotation(org.micro.tcc.tc.annotation.TccTransaction)")
    public void transactionMethod() {

    }

    @Around("transactionMethod()")
    public Object interceptTransactionMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.debug("TCC:TransactionAspect ");
        return tccTransactionInterceptor.interceptTransactionMethod(pjp);
    }


}
