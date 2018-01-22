package com.apollo.dubbo.consumer.controller;

import com.apollo.dubbo.api.IMyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MyController <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/1.
 * @E-mail : 876551724@qq.com
 */
@Controller
public class MyController {
    @Autowired
    private IMyService myInterface;

    @RequestMapping("/test.do")
    public String getTest() {
        System.out.print("11111111111");
        String str = myInterface.helloWorld();
        System.out.print(str);
        return "welcome";
    }
}

