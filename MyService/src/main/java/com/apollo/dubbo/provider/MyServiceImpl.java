package com.apollo.dubbo.provider;

import com.apollo.dubbo.api.IMyService;

/**
 * MyServiceImpl <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/1.
 * @E-mail : 876551724@qq.com
 */
public class MyServiceImpl implements IMyService {
    public String helloWorld(){
        return "hello world!";
    }
}
