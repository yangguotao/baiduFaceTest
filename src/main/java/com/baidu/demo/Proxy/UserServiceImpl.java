package com.baidu.demo.Proxy;

/**
 * @Author: yangguotao
 * @Date: 2020/8/19
 * @Version 1.0
 */
public class UserServiceImpl implements UserService{

    private User user;

    @Override
    public String addUser(User user) {
        System.out.println(user.getName()+"正在登陆...");
        return "success!";
    }

}
