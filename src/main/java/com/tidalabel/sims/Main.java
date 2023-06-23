package com.tidalabel.sims;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception{


        int num = (int) ((Math.random() * 9 + 1) * 100000);


/**
 * 1.配置发件人邮箱信息以及创建一个Java 配置类存放SMTP服务器地址等参数。
 */
        String sendEmailAccount = "jyzdxy@163.com";                            // 发件人邮箱
        String sendEmailPassword = "BBMWCIKPYHTGGDNT";                                        // 发件人密码
        String sendEmailSMTPHost = "smtp.163.com";                        // 发件人邮箱的 SMTP 服务器地址, 此处为Outlook邮箱的SMTP服务器
        String receiveMailAccount = "391363706@qq.com";                                // 收件人邮箱
        Properties props = new Properties();                                        // 使用Java配置类进行配置
        props.setProperty("mail.transport.protocol", "smtp");                        // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", sendEmailSMTPHost);                    // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");                                // 需要请求认证
        final String smtpPort = "25";                                              // 默认端口号设置为587，也可以设置为465，具体取决于SMTP服务器要求的端口号
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        /**
         * 2.创建一个同邮件服务器交互的session
         */
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);                                 // 1. 创建一封邮件
        message.setFrom(new InternetAddress(sendEmailAccount, "ExampleFrom", "UTF-8"));  // 2. From: 发件人
        message.setRecipient(MimeMessage.RecipientType.TO,
                new InternetAddress(receiveMailAccount, "ExampleUser", "UTF-8"));         // 3. To: 收件人
        message.setSubject("", "UTF-8");                                                 // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setContent("<h3>你好,</h3></br><hr><h2>你的验证码是"+num+"</h2>", "text/html;charset=UTF-8"); // 5. Content: 邮件正文
        message.setSentDate(new Date(2023, 6, 20));                                                 // 6. 设置邮件发件时间
        message.saveChanges();                                                         // 7. 保存设置

        /**
         * 3.创建一封格式化的邮件
         */
        Transport transport = session.getTransport();                                     // 1. 根据 Session 获取邮件传输对象
        transport.connect(sendEmailAccount, sendEmailPassword);                             // 2. 使用 邮箱账号 和 密码 连接邮件服务器
        transport.sendMessage(message, message.getAllRecipients());                         // 3. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人,
        transport.close();


    }
}
