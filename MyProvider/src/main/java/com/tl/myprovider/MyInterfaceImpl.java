package com.tl.myprovider;

import com.tl.myinterface.IMyInterface;
import org.springframework.stereotype.Service;

/**
 * com.tl.myservice.MyInterfaceImpl <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/1.
 * @E-mail : tianlei@simpletour.com
 */
@Service("myInterface")
public class MyInterfaceImpl implements IMyInterface {
    public String helloWorld(){
        return "hello world!";
    }
}
