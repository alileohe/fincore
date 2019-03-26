package com.zb.fincore.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * created by fangyang on 2017/9/8 15:36.
 * email:fangyang@zillionfortune.com
 * version:1.0
 * describe:
 */
public class EmailUtils {

	//发件人邮箱
	private String fromEmailAccount;
	//发件人邮箱密码
	private String fromEmailPassword;
	//web邮件服务器地址
	public static final String WEB_EMAIL_ADDRESS="webmail.zillionfortune.com";

	private static Logger logger = LoggerFactory.getLogger(EmailUtils.class);

	/**
	 *
	 * @param toMail 收件人邮箱，多个收件人用,分隔
	 * @param csMail	抄送人邮箱，多个抄送人用,分隔
	 * @param subject	邮件主题
	 * @param content	邮件内容
	 */
	public boolean sendMail(String toMail,String csMail,String subject,String content){

		try {
			if(StringUtils.isEmpty(toMail)){
				return false;
			}

			Properties p = new Properties();
			p.put("mail.smtp.auth", "true");
			p.put("mail.transport.protocol", "smtp");
			p.put("mail.smtp.host", "smtp.zillionfortune.com");
			p.put("mail.smtp.port", "25");

			// 建立会话
			Session session = Session.getInstance(p);
			Message msg = new MimeMessage(session);
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			// 发件人
			msg.setFrom(new InternetAddress(fromEmailAccount));
			// 收件人
			InternetAddress[] iaToList = InternetAddress.parse(toMail);
			msg.setRecipients(Message.RecipientType.TO, iaToList);
			// 抄送人
			if(StringUtils.isNotEmpty(csMail)){
				InternetAddress[] iaToListcs = InternetAddress.parse(csMail);
				msg.setRecipients(Message.RecipientType.CC, iaToListcs);
			}
			// 发送日期
			msg.setSentDate(new Date());
			// 主题
			msg.setSubject(subject);
			// 内容
			msg.setText(content);
			// 显示以html格式的文本内容
			messageBodyPart.setContent(content, "text/html;charset=utf-8");
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);

			// 邮件服务器进行验证
			Transport tran = session.getTransport("smtp");
			tran.connect(WEB_EMAIL_ADDRESS, this.fromEmailAccount,this.fromEmailPassword);
			// 发送
			tran.sendMessage(msg, msg.getAllRecipients());

			logger.info("邮件发送成功");
			return true;
		} catch (MessagingException e) {
			logger.info("邮件发送时异常",e);
			return false;
		}
	}

	public String getFromEmailAccount() {
		return fromEmailAccount;
	}

	public void setFromEmailAccount(String fromEmailAccount) {
		this.fromEmailAccount = fromEmailAccount;
	}

	public String getFromEmailPassword() {
		return fromEmailPassword;
	}

	public void setFromEmailPassword(String fromEmailPassword) {
		this.fromEmailPassword = fromEmailPassword;
	}

	public static void main(String args[]){
		String tomail="fangyang112312@zillionfortune123.com";
		String subject="测试";
		String content="测试邮件的内容";
		String csMail="407455574@qq.com";
		EmailUtils emailUtils=new EmailUtils();
		//emailUtils.setFromEmailAccount("");
		//emailUtils.setFromEmailPassword("");
		emailUtils.sendMail(tomail,null,subject,content);
	}

}
