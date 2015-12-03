package com.imethod.sites.web.email.bean;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MailAuthenticator extends Authenticator{
	private  String username="tenant-backend@iMethod.com";
	private  String password="aaabbbccc";
	
	public MailAuthenticator() {
		super();
	}
	
	/**
	 * 设置验证的用户名和密码
	 */
	public MailAuthenticator(String userName , String password) {
		super();
		this.username = userName;
		this.password = password;
	}
	protected PasswordAuthentication getPasswordAuthentication()
	{   
		return new PasswordAuthentication(this.username,this.password);   
	}   
}
