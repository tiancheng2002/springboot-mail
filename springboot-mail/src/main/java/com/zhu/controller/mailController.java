package com.zhu.controller;

import com.zhu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mailController {

    @Autowired
    JavaMailSenderImpl mailSender;

    private String captcha = null;

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String Register(){
        return "register";
    }

    @RequestMapping("/send")
    public String SendEmail(@RequestParam("toEmail") String toEmail){
        captcha = RandomNumber();
        MailService service = new MailService(toEmail,captcha,mailSender);
        service.start();
        System.out.println(captcha);
        return "send";
    }

    @RequestMapping("/test")
    public String Login(@RequestParam("captcha") String s, Model model){
        if(s.equals(captcha)){
            System.out.println("注册成功！");
            captcha=null;
            return "login";
        }else{
            model.addAttribute("errorMessage","验证码错误，请重新输入！");
            return "send";
        }
    }

//    @RequestMapping("/sendTo")
//    public String sendTo(Model model){
//        MailService service = new MailService("1849530179@qq.com");
//        service.start();
//        model.addAttribute("success","邮件发送成功");
//        return "success";
//    }

    public void Send(String toEmail,String text) {

        //一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送的邮箱标题
        message.setSubject("邮箱验证码");
        //发送者
        message.setFrom("1849530179@qq.com");
        //接收者
        message.setTo(toEmail);
        //发送内容
        message.setText("尊敬的用户，欢迎您注册XXX，验证码："+text+"，请在五分钟之内进行验证！");

        mailSender.send(message);
    }

    //生成六位随机数表示验证码
    public String RandomNumber(){
        return String.valueOf((int)((Math.random()*9+1)*100000));
    }
}
