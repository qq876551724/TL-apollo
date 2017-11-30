package com.apollo.dubbo.consumer.controller.captcha;

import com.apollo.commons.captcha.CaptchaUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * com.apollo.dubbo.consumer.controller.captcha.CaptchaController <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
@RestController
@RequestMapping("/")
public class CaptchaController {
    /**
     * 网页验证码
     * @return
     */
    @RequestMapping(value = "Captcha.image", method = RequestMethod.GET)
    public void service(HttpServletRequest request, HttpServletResponse response){
        try
        {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            //生成随机字串
            String verifyCode = CaptchaUtils.generateVerifyCode(4);

            //存入会话session
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", verifyCode.toLowerCase());
            //生成图片
            int w = 200, h = 80;
            CaptchaUtils.outputImage(w, h, response.getOutputStream(), verifyCode);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
