package com.imethod.module.schedule;

import java.util.Date;

public class EmailMessage 
{
	/**
	 * 邮件的信息,可以自己进行设置。
	 * 为了方便直接全部设置为静态
	 */

//	发件人邮箱
	public  static String FROM = "jqwang@gaoxiaobang.com";
	public  static String FROM_NAME = "jqwang";
//	 抄送人	
	public  static String CC="";
//	 暗送人
	public  static String BCC="";
//	邮件的类型
	public 	static String Email_Content = "text/plain";
//	邮件标题
	public  static String Email_Subject = "Test Email With JavaMail";   
//	邮件头
	public  static String Email_Header = "This Is Email Header";   	
//	内容
	//public  static String Email_Body = "<a href=\"http://www.baidu.com\">This Is Email Body</a>";
//	服务器	比如QQ的可以设置为smtp.qq.com
	public  static String Email_Host = "smtp.exmail.qq.com";
//	是否需要验证用户名和密码
	public 	static boolean validate = true; 	
}
