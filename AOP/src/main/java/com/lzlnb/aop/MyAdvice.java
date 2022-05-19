package com.lzlnb.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
	//规定切入点，在执行这个方法的时候进行切入
	@Pointcut("execution(void com.lzlnb.dao.BookDao.update())")
	public void pointCut() {
	}

	//规定在执行方法之前进行执行
	@Before("pointCut()")
	public void method() {
		System.out.println(System.currentTimeMillis());
	}
}
