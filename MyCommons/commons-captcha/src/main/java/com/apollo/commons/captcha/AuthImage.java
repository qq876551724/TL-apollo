package com.apollo.commons.captcha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Created by 田雷 on 2016/12/1.
 */


public class AuthImage extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    }
}