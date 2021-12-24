package com.zhu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailService extends Thread {

    JavaMailSenderImpl mailSender;
    private String toEmail;
    private String captcha;

    public MailService(String toEmail,String captcha,JavaMailSenderImpl javaMailSender){
        this.toEmail=toEmail;
        this.captcha=captcha;
        this.mailSender=javaMailSender;
    }

    @Override
    public void run() {
        //一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送的邮箱标题
        message.setSubject("邮箱验证码");
        //发送者
        message.setFrom("1849530179@qq.com");
        //接收者
        message.setTo(toEmail);
        //发送内容
        message.setText("尊敬的用户，欢迎您注册XXX，验证码："+captcha+"，请在五分钟之内进行验证！");

        mailSender.send(message);
    }
}
