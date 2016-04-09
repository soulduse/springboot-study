package net.soul.sp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sould on 2016-04-09.
 */
public class CacheAspect {

    private Map<Long, Object> cache = new HashMap<Long, Object>();

    public Object execute(ProceedingJoinPoint joinPoint)throws Throwable{
        Long num = (Long)joinPoint.getArgs()[0];
        if(cache.containsKey(num)){
            System.out.printf("CacheAspect : Cache에서 구함[%d]\n", num);
            return cache.get(num);
        }

        Object result = joinPoint.proceed();
        cache.put(num, result);
        System.out.printf("CacheAspect : Cache에 추가[%d]\n"+num);
        return result;
    }
}
