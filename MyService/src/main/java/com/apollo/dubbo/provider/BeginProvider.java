package com.apollo.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 模拟运行提供者（调试使用）
 * BeginProvider <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/2.
 * @E-mail : tianlei@simpletour.com
 */
public class BeginProvider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:/META-INF/spring/*.xml"});
        System.out.println("start dubbo service...");
        context.start();
        System.out.println("start dubbo service success!");
        System.in.read();
    }
}
