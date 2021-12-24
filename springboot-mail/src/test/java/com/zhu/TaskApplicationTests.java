package com.zhu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void Send() throws MessagingException {
        contextLoads2("我嫩爹！","1849530179@qq.com","1172188789@qq.com");
    }

    @Test
    void contextLoads(String subject,String fromEmail,String toEmail,String text) {

        //一个简单的邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送的邮箱标题
        message.setSubject(subject);
        //发送者
        message.setFrom(fromEmail);
        //接收者
        message.setTo(toEmail);
        //发送内容
        message.setText(text);

        mailSender.send(message);
    }

    @Test
    void contextLoads2(String subject,String fromEmail,String toEmail) throws MessagingException {

        //一个复杂的邮件
        MimeMessage message = mailSender.createMimeMessage();
        //组装，后面一个参数表示允许添加附件和内部资源
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        //发送邮件标题
        helper.setSubject(subject);
        //发送内容，这里直接用html格式表示，对方收到邮件效果是编译后的
        helper.setText("<h1 style='color:red'>hjl是shabi！</h1>",true);
        //附件：可以是图片也可以是word文档之类的
        helper.addAttachment("1.jpg",new File("C:\\Users\\86183\\Pictures\\04.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\86183\\Pictures\\04.jpg"));
        //接收者
        helper.setTo(toEmail);
        //发送者
        helper.setFrom(fromEmail);

        mailSender.send(message);
    }

}
