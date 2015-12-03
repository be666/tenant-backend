package com.imethod.sites.web.email.bean;

import java.util.List;

public class EmailMessage {

	//发件人邮箱
	private   String fromEmail = "backend@sina.com";
	private   String fromName = "后台";
	//邮件的类型
	private   String htmlContentType = "text/html; charset=utf-8";
	private   String emailContent ;
	//邮件标题
    private  String emailSubject = "后台预警";
	//邮件头
	private   String emailHeader = "this is header";
	//内容
	private List<String> emailToList;
	//stmp
	private  String emailHost = "smtp.sina.com";  //"smtp.exmail.qq.com";
	//是否需要验证用户名和密码
	private  boolean validate = true;

	public EmailMessage(String emailContent, List<String> emailToList){
		this.emailContent = emailContent;
		this.emailToList = emailToList;
	}

	public  String getEmailHost() {
		return emailHost;
	}

	public  void setEmailHost(String emailHost) {
		emailHost = emailHost;
	}

	public  boolean isValidate() {
		return validate;
	}

	public  void setValidate(boolean validate) {
		validate = validate;
	}


	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getEmailHeader() {
		return emailHeader;
	}

	public void setEmailHeader(String emailHeader) {
		this.emailHeader = emailHeader;
	}

	public List<String> getEmailToList() {
		return emailToList;
	}

	public void setEmailToList(List<String> emailToList) {
		this.emailToList = emailToList;
	}

	public String getHtmlContentType() {
		return htmlContentType;
	}

	public void setHtmlContentType(String htmlContentType) {
		this.htmlContentType = htmlContentType;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
}
