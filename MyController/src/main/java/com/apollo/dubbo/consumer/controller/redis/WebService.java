package com.apollo.dubbo.consumer.controller.redis;

import com.apollo.commons.redis.RedisUtils;
import com.apollo.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.example.demo.WebService <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/10/27.
 * @E-mail : tianlei@simpletour.com
 */
@RestController
@RequestMapping("/")
public class WebService {
    @Autowired
    private RedisUtils redisUtils;
    @RequestMapping("set")
    public String set(String name,Model model) throws Exception {
        User user = new User();
        user.setName(name);
        user.setAge("10");
        user.setSex("男");
        redisUtils.set(name, user);
        return "success";
    }

    @RequestMapping("set_value")
    public String set_value(String name,String value,Model model) throws Exception {
        redisUtils.set(name, value);
        return "success";
    }

    @RequestMapping("get")
    public String get(String name,Model model) throws Exception {
        Object obj = redisUtils.get(name);
        if(obj instanceof User){
            User user = (User)obj;
            return user.toString();
        }else{
            return obj.toString();
        }
    }
}
