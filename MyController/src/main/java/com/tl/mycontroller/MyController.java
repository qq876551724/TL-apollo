package com.tl.mycontroller;

import com.tl.myinterface.IMyInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * com.tl.mycontroller.MyController <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/1.
 * @E-mail : tianlei@simpletour.com
 */
@Controller
public class MyController {
    @Autowired
    private IMyInterface myInterface;

    @RequestMapping("/test.do")
    public String getTest() {
        System.out.print("11111111111");
        String str = myInterface.helloWorld();
        System.out.print(str);
        return "welcome";
    }
}

