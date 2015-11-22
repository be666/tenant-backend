package com.imethod.module.schedule;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MessagePost {
	private static int connectTimeOut = 5000;
	private static int readTimeOut = 10000;
	private static String requestEncoding = "UTF-8";
	private static final Logger LOG = LoggerFactory.getLogger(MessagePost.class);
	
	public static String sendMessage(String requestUrl, Map<String, String> parameters) {
		HttpURLConnection urlConnection = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator<?> it = parameters.entrySet().iterator(); it.hasNext();) {
				Entry<?, ?> element = (Entry<?, ?>) it.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(),requestEncoding));
				params.append("&");
			}
			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(requestUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setConnectTimeout(connectTimeOut);
			urlConnection.setReadTimeout(readTimeOut);
			urlConnection.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			urlConnection.getOutputStream().write(b, 0, b.length);
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();

			InputStream in = urlConnection.getInputStream();
			byte[] echo = new byte[10 * 1024];
			int len = in.read(echo);
			responseContent = (new String(echo, 0, len)).trim();
			int code = urlConnection.getResponseCode();
			if (code != 200) {
				responseContent = "ERROR" + code;
			}

			Document doc = DocumentHelper.parseText(responseContent);
			if (doc != null) {
				Element root = doc.getRootElement(); // 获取根节点
				if (root != null)
					responseContent = root.getText(); // 拿到根节点的名称
			}

		} catch (IOException e) {
			LOG.error("网络故障:" + e.toString());
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return responseContent;
	}

}

