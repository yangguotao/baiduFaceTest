package com.baidu.demo.Proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author: yangguotao
 * @Date: 2020/8/19
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {
        User user = new User().setName("1123").setPassWord("12345123");
        UserService userService = new UserServiceImpl();
        InvocationHandler userServiceInterceptor = new UserServiceInterceptor(userService);
        userService = (UserService) Proxy.newProxyInstance(userServiceInterceptor.getClass().getClassLoader(),userService.getClass().getInterfaces(),userServiceInterceptor);
        userService.addUser(user);

    }
}
