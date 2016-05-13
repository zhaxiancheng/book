package cn.dream.web.action.privilege;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect @Component//交给spring管理
public class Interceptor {
    @Pointcut("execution(org.apache.struts.action.ActionForward cn.itcast.web.action..*.*(org.apache.struts.action.ActionMapping,org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse))")
    private void actionMethod(){}
    
    @Around("actionMethod()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("拦截到了"+ pjp.getSignature().getName() + "方法");
        return pjp.proceed();
    }
}
