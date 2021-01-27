package com.baidu.demo.Proxy;

import org.springframework.stereotype.Service;

/**
 * @Author: yangguotao
 * @Date: 2020/8/19
 * @Version 1.0
 */
@Service
public interface UserService {
    String addUser(User user);
}
