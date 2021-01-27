package com.baidu.demo.Proxy;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: yangguotao
 * @Date: 2020/8/19
 * @Version 1.0
 */
@Slf4j
public class UserServiceInterceptor implements InvocationHandler {

    private  Object obj;

    public UserServiceInterceptor(Object obj){
        super();
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(null != args && args.length > 0 && args[0] instanceof User){
           User user = (User)args[0];
           if(user.getName().trim().length()<=2){
                log.error("用户名不符合规范,请检查。。。");
           }
           if(user.getPassWord().trim().length()<=6){
               log.error("用户密码不符合规范。。。");
           }
        }
        return null;
    }
}
