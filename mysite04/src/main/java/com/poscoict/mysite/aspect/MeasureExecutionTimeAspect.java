package com.poscoict.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..*.repository.*.*(..)) || execution(* *..*.service.*.*(..)) || execution(* *..*.controller.*.*(..)) ")
	public Object aroundAdvice(ProceedingJoinPoint pip) throws Throwable {
		//before
		StopWatch sw = new StopWatch();
		sw.start();
		
		
		
		Object result = pip.proceed();
		
		//after
		
		sw.stop();
		Long totalTime = sw.getTotalTimeMillis();
		
		String className = pip.getTarget().getClass().getName();
		String methodName = pip.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[Execution Time][" + taskName + "]" + totalTime + "Millis");
		
		
		return result;
	}
}
