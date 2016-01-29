package com.ebao.life.gavin.bo;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.ebao.life.gavin.util.ToolUtil;

/**
* @ClassName: Sendmail
* @Description: 发送Email
* @author: 孤傲苍狼
* @date: 2015-1-12 下午9:42:56
*
*/ 
public class SendMailBO {
   /**
    * 
    * @param attachFilePath 附件路径
    * @param from 发件人
    * @param to 收件人
    * @param mailSubject 邮件主题
    * @param mailContent 邮件内容
    * @throws Exception
    */
    public void sendMail(String attachFilePath, String from, String to, String mailSubject, String mailContent) throws Exception{
    	 Properties prop = new Properties();
         prop.setProperty("mail.host", "10.1.190.1");
         prop.setProperty("mail.transport.protocol", "smtp");
         prop.setProperty("mail.smtp.auth", "true");
         //使用JavaMail发送邮件的5个步骤
         //1、创建session
         Session session = Session.getInstance(prop);
         //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
         session.setDebug(true);
         //2、通过session得到transport对象
         Transport ts = session.getTransport();
         //3、连上邮件服务器
         ts.connect("10.1.190.1", "ebao.zhangxh", "Ebaotech2010");
         //4、创建邮件
//         String attachFilePath = "E:\\heheda.xlsx";
         Message message = createAttachMail( session,  attachFilePath,  mailSubject, mailContent,  from,  to);
         //5、发送邮件
         ts.sendMessage(message, message.getAllRecipients());
         ts.close();
    }
    
//    public void sendMail()throws Exception{
//    	
//    }
    
    /**
     * 发送邮件方法
     * @param session
     * @param attachFilePath 附件绝对路径
     * @param mailSubject 邮件主题
     * @param mailContent 邮件内容
     * @param from 发件人
     * @param to 收件人
     * @return
     * @throws Exception
     */
    public MimeMessage createAttachMail(Session session, String attachFilePath, String mailSubject,String mailContent, String from, String to) throws Exception{
        MimeMessage message = new MimeMessage(session);
        
        //设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress(from));
        //收件人
        InternetAddress[] addrarr = null;
    	String[] strs = to.split(";");
    	addrarr = new InternetAddress[strs.length];
    	for (int i = 0; i < addrarr.length; i++) {
    		addrarr[i] = new InternetAddress(strs[i]);
		}
//        {new InternetAddress(from),new InternetAddress(to)};
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setRecipients(Message.RecipientType.TO, addrarr);
        //邮件标题
        message.setSubject(mailSubject);
        
        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(mailContent, "text/html;charset=UTF-8");
        
        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        
        if(!ToolUtil.isEmpty(attachFilePath)){
        	 //创建邮件附件
            MimeBodyPart attach = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(attachFilePath));
            attach.setDataHandler(dh);
            attach.setFileName(dh.getName());  //
            mp.addBodyPart(attach);
        }
        mp.setSubType("mixed");
        
        message.setContent(mp);
        message.saveChanges();
        //本地存储邮件
        String mailRoot = "C:\\tmp";
        File mailDict = new File(mailRoot);
        if(!mailDict.exists()){
        	mailDict.mkdir();
        }
        String mailPath = mailRoot + File.separator + "attachMail.eml";
        message.writeTo(new FileOutputStream(mailPath));
        //返回生成的邮件
        return message;
    }
}