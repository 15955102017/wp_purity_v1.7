package com.github.wp.system.web.spring.aspectj;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.web.bind.annotation.CurrentUser;

/**
 * 自定义系统日志的切面
 * @author wangping
 * @version 1.0
 * @since 2016年1月19日, 下午5:48:04
 */
@Aspect  
@Component 
public class SystemLogAspectj {

	private static final Logger logger = Logger.getLogger(SystemLogAspectj.class);  
	  
    //Controller层切点  
    @Pointcut("@annotation(com.github.wp.system.web.spring.aspectj.SystemLog)")  
    public void controllerAspect() {  
    	
    }  
    
    //切点执行前的操作
    @Before("controllerAspect()")  
    public void doBefore(JoinPoint joinPoint) throws Exception {  
    	String advice = getControllerMethodDescription(joinPoint);
    	Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
        //读取session中的用户    
        SysUser user = (SysUser)session.getAttribute(CurrentUser.User.CURRENT_USER.getInfo());    
        //请求的IP 
        MDC.put("className", joinPoint.getTarget().getClass().getName());
        MDC.put("methodName", joinPoint.getSignature().getName());
        MDC.put("id",user.getUsername());
		MDC.put("userName",user.getRealName());
    	logger.info(advice);
   }  
    
    //获取切点（Controller）中方法的描述信息
    public static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class<?> targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
         for (Method method : methods) {  
             if (method.getName().equals(methodName)) {  
                Class<?>[] clazzs = method.getParameterTypes();  
                 if (clazzs.length == arguments.length) {  
                     description = method.getAnnotation(SystemLog.class).description().getInfo();  
                     break;  
                }  
            }  
        }  
         return description;  
    }
    
    //Controller层抛出异常后的动作
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")  
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Exception{  
    	StringBuffer str = new StringBuffer();
    	str.append(joinPoint.getTarget().getClass().getName() + "中的"  
                + joinPoint.getSignature().getName() + "方法抛出" + e.getClass().getName()  
                + "异常");
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {  
            for (int i = 0; i < joinPoint.getArgs().length; i++) {  
                str.append("参数：--" + joinPoint.getArgs()[i].toString());
            }  
        }
        String advice = getControllerMethodDescription(joinPoint);
    	Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
        //读取session中的用户    
        SysUser user = (SysUser)session.getAttribute(CurrentUser.User.CURRENT_USER.getInfo());    
        //请求的IP 
        MDC.put("className", joinPoint.getTarget().getClass().getName());
        MDC.put("methodName", joinPoint.getSignature().getName());
        MDC.put("id",user.getUsername());
		MDC.put("userName",user.getRealName());
		logger.info(advice+"失败");
    	logger.error(str);
    }  
    
    //切点执行完的动作
//    @AfterReturning("controllerAspect()")  
//    public void doAfter(JoinPoint joinPoint) throws Exception {  
//    	String advice = getControllerMethodDescription(joinPoint);
//    	Subject currentUser = SecurityUtils.getSubject();
//    	Session session = currentUser.getSession();
//        //读取session中的用户    
//        SysUser user = (SysUser)session.getAttribute(Constants.CURRENT_USER);    
//        //请求的IP 
//        MDC.put("className", joinPoint.getTarget().getClass().getName());
//        MDC.put("methodName", joinPoint.getSignature().getName());
//        MDC.put("id",user.getUsername());
//		MDC.put("userName",user.getRealName());
//		logger.info(advice+"成功");
//    }
}
