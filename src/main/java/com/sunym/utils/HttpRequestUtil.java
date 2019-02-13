package com.sunym.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpRequestUtil {

	public static String doGet(String url, String charset) {
		if (null == charset) {
			charset = "utf-8";
		}

		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		String result = null;

		try {
			httpClient = HttpClientBuilder.create().build();
			httpGet = new HttpGet(url);

			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String doPost(String url, Map<String, String> map, String charset) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClientBuilder.create().build();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String doPost(String url, Map<String, String> headParma, Map<String, String> bodyParam, String charset) {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = HttpClientBuilder.create().build();
			httpPost = new HttpPost(url);
			// 设置Head参数
			Iterator iterator = headParma.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				httpPost.setHeader(elem.getKey(), elem.getValue());
			}

			// 设置Body参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			iterator = bodyParam.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			return "";
		}
		return result;
	}

	public static String doPost(String url, String param, String charset) {
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		String response = "";
		try {
			URL httpUrl = null; // HTTP URL类 用这个类来创建连接
			// 创建URL
			httpUrl = new URL(url);
			// 建立连接
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("connection", "keep-alive");
			conn.setUseCaches(false);// 设置不要缓存
			conn.setInstanceFollowRedirects(true);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			// POST请求
			out = new OutputStreamWriter(conn.getOutputStream());
			out.write(param);
			out.flush();
			// 读取响应
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String lines;
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				response += lines;
			}
			reader.close();
			// 断开连接
			conn.disconnect();
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return response;
	}

	public static  String getBasicAuthEncode(String client, String secret) {
		String auth = client + ":" + secret;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		return authHeader;
	}
}
