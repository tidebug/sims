package com.tidalabel.sims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

@Controller
public class StudentController {

    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost:3306/sims?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    final String USER = "root";
    int yzm = 0;
    final String PASS = "123456";
    String email = "";

    /**
     * 服务器一启动就跳转到登录页面
     */
    @RequestMapping(value = "/")
    public String page_login() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // 清屏命令
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        return "page_login";
    }

    /**
     * 当登录页面发送登录请求时候处理数据，从数据库中判断用户名和密码是否正确
     * 返回结果：page_index
     * page_error
     */
    @RequestMapping(value = "/do_login")
    public String do_login(HttpServletRequest request, Model model) throws Exception {
        String stu_ref_1 = null, stu_passwd_1 = null;
        String stu_ref = request.getParameter("stu_ref");
        String stu_passwd = request.getParameter("stu_passwd");

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM t_student where stu_ref=" + stu_ref + " and stu_passwd=" + stu_passwd + "";
            ResultSet rs = stmt.executeQuery(sql);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // 清屏命令
            System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.println("当前页面:【】");
            System.out.println("当前位置:"+this.getClass());
            System.out.println("----------------------------------");
            while (rs.next()) {

                stu_ref_1 = rs.getString("stu_ref");
                stu_passwd_1 = rs.getString("stu_passwd");
                System.out.println("已展开:");
                System.out.println("\t用户名:"+stu_ref);
                System.out.println("\t密码:"+stu_passwd);


            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        if (stu_ref_1.equals(stu_ref) && stu_passwd_1.equals(stu_passwd)) {

            return "page_index";
        } else {
            return "page_error";
        }

    }

    @RequestMapping("/page_logup")
    public String page_logout() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_logup";
    }

    @RequestMapping("/do_logup")
    public String do_logup(HttpServletRequest request) throws Exception {

        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String text1 = request.getParameter("stu_ref");
        String text2 = request.getParameter("stu_passwd");
        String text3 = request.getParameter("stu_email");

        String sql = "INSERT into t_student(stu_ref,stu_passwd,stu_email) VALUES(?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        ptmt.setString(1, text1);
        ptmt.setString(2, text2);
        ptmt.setString(3, text3);

        ptmt.execute();
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        System.out.println("\t 用户名:"+text1);
        System.out.println("\t 密码:"+text2);
        System.out.println("\t 电子邮箱:"+text3);
        ptmt.close();
        conn.close();

        return "page_ok";
    }

    /**
     * 捕获'page_index'的请求
     * 呈现出page_index的内容
     */
    @RequestMapping(value = "/page_index")
    public String page_index() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_index";
    }

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @RequestMapping(value = "/page_add")
    public String page_add() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_add";
    }

    /**
     * 捕获 'do_add'的请求
     * 添加学生信息
     *
     * @return
     */
    @RequestMapping(value = "/do_add")
    public String do_add(HttpServletRequest request) throws Exception {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String text1 = request.getParameter("text1");
        String text2 = request.getParameter("text2");
        String text3 = request.getParameter("text3");
        String text4 = request.getParameter("text4");
        String text5 = request.getParameter("text5");
        String text6 = request.getParameter("text6");
        String text7 = request.getParameter("text7");
        String text8 = request.getParameter("text8");
        String sql = "insert into t_student values(?,?,?,?,?,?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        ptmt.setString(1, text1);
        ptmt.setString(2, text2);
        ptmt.setString(3, text3);
        ptmt.setString(4, text4);
        ptmt.setString(5, text5);
        ptmt.setString(6, text6);
        ptmt.setString(7, text7);
        ptmt.setString(8, text8);
        ptmt.execute();
        System.out.println("已展开");
        System.out.println("\t账户名:" + text1);
        System.out.println("\t密码:" + text2);
        System.out.println("\t姓名:" + text3);
        System.out.println("\t年龄:" + text4);
        System.out.println("\t身份证号码:" + text5);
        System.out.println("\t性别:" + text6);
        System.out.println("\t电话号码:" + text7);
        System.out.println("\t电子邮箱:" + text8);
        ptmt.close();
        conn.close();
        return "page_ok";
    }

    @RequestMapping("/page_delete")
    public String page_delete() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_delete";
    }

    /***
     * 一旦捕获到删除就交给下面的请求做
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/do_delete")
    public String do_delete(HttpServletRequest request) throws Exception {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String text1 = request.getParameter("text1");

        String sql = "delete  from t_student where stu_ref=?";
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        ptmt.setString(1, text1);

        ptmt.execute();
        System.out.println("已删除的账户:" + text1);

        ptmt.close();
        conn.close();
        return "page_ok";
    }

    @RequestMapping("/page_update")
    public String page_update() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_update";
    }

    @RequestMapping("/do_update")
    public String do_update(HttpServletRequest request) throws Exception {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        String text1 = request.getParameter("text1");
        String text2 = request.getParameter("text2");
        String text3 = request.getParameter("text3");
        String text4 = request.getParameter("text4");
        String text5 = request.getParameter("text5");
        String text6 = request.getParameter("text6");
        String text7 = request.getParameter("text7");
        String text8 = request.getParameter("text8");
        String sql = "update  t_student set stu_passwd=?,stu_name=?,stu_age=?,stu_id=?,stu_sex=?,stu_phonenumber=?,stu_email=? where stu_ref=?";
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
        ptmt.setString(1, text2);
        ptmt.setString(2, text3);
        ptmt.setString(3, text4);
        ptmt.setString(4, text5);
        ptmt.setString(5, text6);
        ptmt.setString(6, text7);
        ptmt.setString(7, text8);
        ptmt.setString(8, text1);
        ptmt.execute();
        System.out.println("已执行更新");
        System.out.println("\t账户名:" + text1);
        System.out.println("\t密码:" + text2);
        System.out.println("\t姓名:" + text3);
        System.out.println("\t年龄:" + text4);
        System.out.println("\t身份证号码:" + text5);
        System.out.println("\t性别:" + text6);
        System.out.println("\t电话号码:" + text7);
        System.out.println("\t电子邮箱:" + text8);
        ptmt.close();
        conn.close();
        return "page_ok";
    }

    @RequestMapping("/page_select")
    public String page_select() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_select";
    }

    @RequestMapping("/do_select")
    public String do_select(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String stu_ref_1 = null;
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        String stu_ref = request.getParameter("stu_ref");

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM t_student where stu_ref=" + stu_ref;
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("请求:["+sql+"]");
            response.setContentType("text/html");
            while (rs.next()) {

                String text1 = rs.getString("stu_ref");
                String text2 = rs.getString("stu_passwd");
                String text3 = rs.getString("stu_name");
                String text4 = rs.getString("stu_age");
                String text5 = rs.getString("stu_id");
                String text6 = rs.getString("stu_sex");
                String text7 = rs.getString("stu_phonenumber");
                String text8 = rs.getString("stu_email");
                PrintWriter out = response.getWriter();

                out.println("" +

                        "    <table>" +
                        "        <tr>\n" +
                        "            <th>用户名</th>\n" +
                        "            <th>密码</th>\n" +
                        "            <th>姓名</th>\n" +
                        "            <th>年龄</th>\n" +
                        "            <th>身份证号码</th>\n" +
                        "            <th>电话号码</th>\n" +
                        "            <th>电子邮箱</th>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td>" + text1 + "</td>\n" +
                        "            <td>" + text2 + "</td>\n" +
                        "            <td>" + text3 + "</td>\n" +
                        "            <td>" + text4 + "</td>\n" +
                        "            <td>" + text5 + "</td>\n" +
                        "            <td>" + text6 + "</td>\n" +
                        "            <td>" + text7 + "</td>\n" +
                        "            <td>" + text8 + "</td>\n" +
                        "        </tr>\n" +
                        "    </table>");


            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return "page_ok";
    }


    @RequestMapping("/page_forget")
    public String page_forget() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_forget";
    }

    @RequestMapping("/do_forget")

    public String do_forget(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String text3 = request.getParameter("email");
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");

        int num = (int) ((Math.random() * 9 + 1) * 100000);
        yzm = num;


/**
 * 1.配置发件人邮箱信息以及创建一个Java 配置类存放SMTP服务器地址等参数。
 */
        String sendEmailAccount = "jyzdxy@163.com";                            // 发件人邮箱
        String sendEmailPassword = "BBMWCIKPYHTGGDNT";                                        // 发件人密码
        String sendEmailSMTPHost = "smtp.163.com";                        // 发件人邮箱的 SMTP 服务器地址, 此处为Outlook邮箱的SMTP服务器
        String receiveMailAccount = text3;                                // 收件人邮箱
        email = text3;
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
        message.setContent("<h3>你好," + text3 + "</h3></br><hr><h2>你的验证码是" + num + "</h2>", "text/html;charset=UTF-8"); // 5. Content: 邮件正文
        message.setSentDate(new Date(2023, 6, 21));                                                 // 6. 设置邮件发件时间
        message.saveChanges();                                                         // 7. 保存设置

        /**
         * 3.创建一封格式化的邮件
         */
        Transport transport = session.getTransport();                                     // 1. 根据 Session 获取邮件传输对象
        transport.connect(sendEmailAccount, sendEmailPassword);                             // 2. 使用 邮箱账号 和 密码 连接邮件服务器
        transport.sendMessage(message, message.getAllRecipients());                         // 3. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人,
        transport.close();
        return "page_forget2";
    }

    @RequestMapping("/page_forget2")
    public String page_forget2() {
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        return "page_forget2";
    }

    @RequestMapping("/do_forget2")
    public String do_forget2(HttpServletRequest request) throws Exception {
        int yzm_1 = Integer.parseInt(request.getParameter("yzm"));
        System.out.println("yzm_1" + yzm_1);
        System.out.println("yzm" + yzm);
        System.out.println("yzm_1与yzm类型对比" + (yzm == yzm_1));
        String newpasswd = request.getParameter("newpasswd");
        System.out.println("捕获到一个请求:"+Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println("当前页面:【】");
        System.out.println("当前位置:"+this.getClass());
        System.out.println("----------------------------------");
        if (yzm_1 == yzm) {

            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "update  t_student set stu_passwd=? where stu_email=?";
            PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
            ptmt.setString(1, newpasswd);
            ptmt.setString(2, email);
            ptmt.execute();
            System.out.println("\t\n");
            System.out.println("==============");
            System.out.print("忘记密码:");
            System.out.println("新密码是" + newpasswd);
            System.out.println("邮箱是:" + email);
            System.out.println("验证码是" + yzm);

            ptmt.close();
            conn.close();
        }
        return "page_ok";
    }

}
