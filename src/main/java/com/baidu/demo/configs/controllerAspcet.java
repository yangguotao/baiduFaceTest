package com.baidu.demo.configs;

import com.baidu.demo.ApiResult.ApiResult;
import com.baidu.demo.ApiResult.SuccessResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author: yangguotao
 * @Date: 2021/1/25
 * @Version 1.0
 */
@Slf4j
@Component
@Aspect
public class controllerAspcet {
    @Pointcut("execution(* com.baidu.demo.controller.*.*(..))")
    public void pointCut_() {
    }

    //@Before(value = "pointCut_()")
    public Object testBefore(JoinPoint point) {

        Arrays.stream(point.getArgs()).forEach(System.out::println);
        System.out.println(point.getArgs().toString());
        Object[] objects = point.getArgs();
        for (int i = 0; i < point.getArgs().length; i++) {
            if (point.getArgs()[i] instanceof String) {
                point.getArgs()[i] = ((String) point.getArgs()[i]) + "-testAop_Before";
            }
            if (point.getArgs()[i] instanceof Integer && point.getArgs()[i] != null) {
                point.getArgs()[i] = ((Integer) point.getArgs()[i]) + 1;
            }
        }
        Arrays.stream(point.getArgs()).forEach(System.out::println);
        return point;
    }

    /**
     * @param proceedingJoinPoint proceedingJoinPoint是JoinPoint子继承类，拥有JoinPoint相关属性。
     *                            JoinPoint提供对连接点可用状态的静态访问以及有关该状态的静态信息。
     *                            可以使用特殊格式 thisJoinPoint  从建议正文中获取此信息。该反射
     *                            信息的主要用途是用于跟踪和记录应用程序。为了支持@AJ方面的建议，
     *                            ProceedingJoinPoint 公开了 proced（..）方法，proced(..)分为有参
     *                            和无参两种方式，无参直接执行连接点方法，有参是将新参数数据传入
     *                            执行连接点方法。
     * @return
     * @throws Throwable
     */
    // @Around(value = "pointCut_()")
    public Object testAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LocalDateTime localDateTimeStart = LocalDateTime.now();
        log.warn("1: ==========================环绕开始时间：{}==============================", localDateTimeStart);
        //获取方法参数值数组
        Object[] args = proceedingJoinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String && args[i] != null) {
                args[i] = ((String) args[i]) + "-testAop_Around";
                log.info("2: + -testAop_Around");
            }
            if (args[i] instanceof Integer && args[i] != null) {
                args[i] = ((Integer) args[i]) + 1;
                log.info("3: + 1");
            }
        }
        log.warn("4: controller开始，传入参数如下");
        Arrays.stream(args).forEach(System.out::println);
        Object resultValue = proceedingJoinPoint.proceed(args);
        log.warn("6: controller结束得到返回结果={}", resultValue.toString());
        Object result = ((ApiResult) resultValue).getData();
        result = result.toString() + "且大力出奇迹";
        ((ApiResult) resultValue).setData(result);
        log.warn("7: 修改controller返回结果值={}", resultValue.toString());
        log.warn("8: ==============环绕结束时间：{}============总用时：{} mm==============================", LocalDateTime.now(),
                Duration.between(localDateTimeStart, LocalDateTime.now()).toMillis());
        return resultValue;
    }

    @Around(value = "pointCut_()")
    public Object getRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LocalDateTime localDateTimeStart = LocalDateTime.now();
        log.warn("==========================环绕开始时间：{}==============================", localDateTimeStart);

        //获取方法参数值数组
        Object[] args = proceedingJoinPoint.getArgs();
        Arrays.stream(args).forEach(System.out::println);
        //得到其方法签名
        Signature signature = proceedingJoinPoint.getSignature();
        log.info("方法签名：signature={}", signature.toString());
        MethodSignature methodSignature = (MethodSignature) signature;

        //获取方法名
        Method method = methodSignature.getMethod();
        log.info("方法名称为：{}", method.getName());
        //获取方法参数类型数组
        Class[] parameterTypes = methodSignature.getParameterTypes();
        String[] parameterNames = methodSignature.getParameterNames();
        log.info("方法参数类型列表: {}", parameterTypes);
        log.info("方法参数名称列表: {}", parameterNames);
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            if (args[i] instanceof String && args[i] != null) {
                args[i] = ((String) args[i]) + "-testAop_Around";
                log.info("加-testAop_Around");
            }
            if (args[i] instanceof Integer && args[i] != null) {
                args[i] = ((Integer) args[i]) + 1;
                ;

            }
        }
        Object resultValue = proceedingJoinPoint.proceed(args);
        System.out.println(resultValue.toString());

        Object result = ((ApiResult) resultValue).getData();
        result = result.toString() + "且大力出奇迹";
        ((ApiResult) resultValue).setData(result);

        System.out.println(proceedingJoinPoint.getSignature().getName());
        log.warn("==============环绕结束时间：{}============总用时：{} mm==============================", LocalDateTime.now(),
                Duration.between(localDateTimeStart, LocalDateTime.now()).toMillis());
        return resultValue;
    }

    //@AfterReturning(value = "pointCut_()",returning = "retValue")
    public Object methodBefore(JoinPoint joinPoint, Object retValue) throws Throwable {
        //获取基础信息
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("请求来源：{}", request.getRemoteAddr());
        log.info("请求地址：{}", request.getRequestURI().toString());
        log.info("请求方式：{}", request.getMethod());
        log.info("请求方法：{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数：{}", Arrays.toString(joinPoint.getArgs()));
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //返回一个Type对象，表示由该方法对象表示的方法的正式返回类型。
        //比如public List<User> getAll();那么返回的是List<User>
        Type genericReturnType = method.getGenericReturnType();
        //获取实际返回的参数名
        String returnTypeName = genericReturnType.getTypeName();
        String methodName = methodSignature.getName();
        System.out.println(methodName + "的返回参数是：" + returnTypeName);

        System.out.println(retValue.toString());
        System.out.println(((ApiResult) retValue).getData());
        Object result = ((ApiResult) retValue).getData();
        result = result.toString().replaceAll("华煤", "大力出奇迹");
        ((ApiResult) retValue).setData(result);
        System.out.println(retValue.toString());
        return retValue;
    }

    /**
     * @param joinPoint 提供对连接点可用状态的静态访问以及有关该状态的静态信息。
     *                  可以使用特殊格式<code> thisJoinPoint <code>从建议正文中获取此信息。
     *                  该反射信息的主要用途是用于跟踪和记录应用程序。
     * @return
     * @throws Throwable
     */
    //@After(value = "pointCut_()")
    public void testAfter(JoinPoint joinPoint) {
        log.info("======After开始=====");
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            log.info("获取参数值-" + (i + 1) + ": {}", joinPoint.getArgs()[i]);
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //目标方法参数类型
        Type[] parameterTypes = methodSignature.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            log.info("目标方法参数类型-" + (i + 1) + "：{}", parameterTypes[i]);
        }
        //目标方法名称
        String[] parameterNames = methodSignature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            log.info("目标方法参数名称-" + (i + 1) + "：{}", parameterNames[i]);
        }
        //目标方法
        Method method = methodSignature.getMethod();
        log.info("目标方法名称：{}", method.getName());
        //返回值类型
        Type returnType = method.getGenericReturnType();
        log.info("目标返回值类型：{}", returnType.getTypeName());
        log.info("======After结束=====");
    }

    /**
     * 返回通知
     * @param joinPoint
     * @param retValue  返回结果集
     * @return
     */
    //@AfterReturning(value = "pointCut_()",returning = "retValue")
    public Object testAfterReturning(JoinPoint joinPoint, Object retValue) {
        Object result = ((ApiResult<?>) retValue).getData();
        log.info("返回对象：{}",retValue.toString());
        log.info("修改前参数：{}",result);
        if (result instanceof String && ((String) result).contains("-aopTest")){
            result =  ((String) result).replaceAll("-aopTest","大力出奇迹");
        }
        ((ApiResult) retValue).setData(result);
      log.info("修改完成后值 = {}",result);
        return retValue;
    }
    /**
     * 返回通知
     * @param joinPoint
     * @return
     */
    //@AfterThrowing(value = "pointCut_()",throwing = "throwable")
    public Object testAfterReturning(JoinPoint joinPoint,Throwable throwable) {
//        Object result = ((ApiResult<?>) retValue).getData();
//        log.info("返回对象：{}",retValue.toString());
//        log.info("修改前参数：{}",result);
//        if (result instanceof String && ((String) result).contains("-aopTest")){
//            result =  ((String) result).replaceAll("-aopTest","大力出奇迹");
//        }
//        ((ApiResult) retValue).setData(result);
//        log.info("修改完成后值 = {}",result);
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String classname = joinPoint.getTarget().getClass().getSimpleName();
        String cc =  methodSignature.getName();
        log.error("类：{}，方法{}错误",classname,cc);
        return new SuccessResult<>("信息异常！");
    }
}
