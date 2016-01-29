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
* @Description: ����Email
* @author: �°�����
* @date: 2015-1-12 ����9:42:56
*
*/ 
public class SendMailBO {
   /**
    * 
    * @param attachFilePath ����·��
    * @param from ������
    * @param to �ռ���
    * @param mailSubject �ʼ�����
    * @param mailContent �ʼ�����
    * @throws Exception
    */
    public void sendMail(String attachFilePath, String from, String to, String mailSubject, String mailContent) throws Exception{
    	 Properties prop = new Properties();
         prop.setProperty("mail.host", "10.1.190.1");
         prop.setProperty("mail.transport.protocol", "smtp");
         prop.setProperty("mail.smtp.auth", "true");
         //ʹ��JavaMail�����ʼ���5������
         //1������session
         Session session = Session.getInstance(prop);
         //����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
         session.setDebug(true);
         //2��ͨ��session�õ�transport����
         Transport ts = session.getTransport();
         //3�������ʼ�������
         ts.connect("10.1.190.1", "ebao.zhangxh", "Ebaotech2010");
         //4�������ʼ�
//         String attachFilePath = "E:\\heheda.xlsx";
         Message message = createAttachMail( session,  attachFilePath,  mailSubject, mailContent,  from,  to);
         //5�������ʼ�
         ts.sendMessage(message, message.getAllRecipients());
         ts.close();
    }
    
//    public void sendMail()throws Exception{
//    	
//    }
    
    /**
     * �����ʼ�����
     * @param session
     * @param attachFilePath ��������·��
     * @param mailSubject �ʼ�����
     * @param mailContent �ʼ�����
     * @param from ������
     * @param to �ռ���
     * @return
     * @throws Exception
     */
    public MimeMessage createAttachMail(Session session, String attachFilePath, String mailSubject,String mailContent, String from, String to) throws Exception{
        MimeMessage message = new MimeMessage(session);
        
        //�����ʼ��Ļ�����Ϣ
        //������
        message.setFrom(new InternetAddress(from));
        //�ռ���
        InternetAddress[] addrarr = null;
    	String[] strs = to.split(";");
    	addrarr = new InternetAddress[strs.length];
    	for (int i = 0; i < addrarr.length; i++) {
    		addrarr[i] = new InternetAddress(strs[i]);
		}
//        {new InternetAddress(from),new InternetAddress(to)};
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setRecipients(Message.RecipientType.TO, addrarr);
        //�ʼ�����
        message.setSubject(mailSubject);
        
        //�����ʼ����ģ�Ϊ�˱����ʼ����������������⣬��Ҫʹ��charset=UTF-8ָ���ַ�����
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(mailContent, "text/html;charset=UTF-8");
        
        //���������������ݹ�ϵ
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        
        if(!ToolUtil.isEmpty(attachFilePath)){
        	 //�����ʼ�����
            MimeBodyPart attach = new MimeBodyPart();
            DataHandler dh = new DataHandler(new FileDataSource(attachFilePath));
            attach.setDataHandler(dh);
            attach.setFileName(dh.getName());  //
            mp.addBodyPart(attach);
        }
        mp.setSubType("mixed");
        
        message.setContent(mp);
        message.saveChanges();
        //���ش洢�ʼ�
        String mailRoot = "C:\\tmp";
        File mailDict = new File(mailRoot);
        if(!mailDict.exists()){
        	mailDict.mkdir();
        }
        String mailPath = mailRoot + File.separator + "attachMail.eml";
        message.writeTo(new FileOutputStream(mailPath));
        //�������ɵ��ʼ�
        return message;
    }
}