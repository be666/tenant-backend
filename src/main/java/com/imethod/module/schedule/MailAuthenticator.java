package com.imethod.module.schedule;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MailAuthenticator extends Authenticator{
	private  String username="jqwang@gaoxiaobang.com";
	private  String password="zxc156778";
	
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
