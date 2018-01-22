package com.apollo.dubbo.consumer.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.apollo.commons.redis.RedisUtils;
import com.apollo.commons.utils.DecriptUtils;
import com.apollo.entity.JsonObjectResult;
import com.apollo.entity.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * UserController <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/10.
 * @E-mail : 876551724@qq.com
 */
@RestController
public class UserController {
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/index")
    public Object getList(){
        return "1111";
    }
    @RequestMapping("/index.jhtml")
    public ModelAndView getIndex(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @RequestMapping("/exceptionForPageJumps.jhtml")
    public ModelAndView exceptionForPageJumps(HttpServletRequest request) throws Exception {
        throw new RuntimeException("NULL_OBJ");
    }

    @RequestMapping(value="/businessException.json", method=RequestMethod.POST)
    @ResponseBody
    public String businessException(HttpServletRequest request) {
        throw new RuntimeException("NULL_OBJ");
    }

    @RequestMapping(value="/otherException.json", method=RequestMethod.POST)
    @ResponseBody
    public String otherException(HttpServletRequest request) throws Exception {
        throw new Exception();
    }

    //跳转到注册页面
    @RequestMapping("/reg.jhtml")
    public ModelAndView reg() throws Exception {
        ModelAndView model = new ModelAndView("reg");
        return model;
    }

    //跳转到登录页面
    @RequestMapping("/login.jhtml")
    public ModelAndView login() throws Exception {
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    //跳转到登录成功页面
    @RequestMapping("/success.jhtml")
    public ModelAndView loginsuccess() throws Exception {
        ModelAndView mav = new ModelAndView("success");
        return mav;
    }

    @RequestMapping("/newPage.jhtml")
    public ModelAndView newPage() throws Exception {
        ModelAndView mav = new ModelAndView("newPage");
        return mav;
    }

    @RequestMapping("/newPageNotAdd.jhtml")
    public ModelAndView newPageNotAdd() throws Exception {
        ModelAndView mav = new ModelAndView("newPageNotAdd");
        return mav;
    }

    /**
     * 验证用户名和密码
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/checkLogin.json",method=RequestMethod.POST)
    @ResponseBody
    public String checkLogin(HttpServletRequest request,String username,String password,String auth) {
        JsonObjectResult result = new JsonObjectResult();
        try{
            String Auth =  (String)request.getSession(true).getAttribute("auth");
            if(!Auth.equalsIgnoreCase(auth)){
                result.setSuccess(false);
                result.setMessage("验证码错误");
                return JSON.toJSONString(result);
            }
            User user = (User)redisUtils.get(username);
            UsernamePasswordToken token = new UsernamePasswordToken(username, DecriptUtils.Encrypt(password,user.getPwd()));
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()){
                //使用shiro来验证
                token.setRememberMe(true);
                currentUser.login(token);//验证角色和权限
            }
        }catch(Exception ex){
            throw new RuntimeException("LOGIN_VERIFY_FAILURE");
        }
        result.setSuccess(true);;
        return JSON.toJSONString(result);
    }

    /**
     * 验证用户名和密码
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/reg.json",method=RequestMethod.POST)
    @ResponseBody
    public String regUser(String username,String password) {
        JsonObjectResult result = new JsonObjectResult();
        try{
            User user = new User();
            user.setName(username);
            user.setAge("10");
            user.setSex("男");
            user.setPwd(DecriptUtils.Encrypt(password));
            redisUtils.set(username, user);
        }catch(Exception ex){
            throw new RuntimeException("REG_FAILURE");
        }
        result.setSuccess(true);
        return JSON.toJSONString(result);
    }

    /**
     * 退出登录
     */
    @RequestMapping(value="/logout.json",method=RequestMethod.POST)
    @ResponseBody
    public String logout() {
        JsonObjectResult result = new JsonObjectResult();
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            result.setSuccess(true);
        }catch (Exception e){
            throw new RuntimeException("LOGOUT_FAILURE");
        }
        return JSON.toJSONString(result);
    }
}
