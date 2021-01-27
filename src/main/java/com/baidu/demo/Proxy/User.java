package com.baidu.demo.Proxy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: yangguotao
 * @Date: 2020/8/19
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User {

    private String Name;

    private String PassWord;
}
