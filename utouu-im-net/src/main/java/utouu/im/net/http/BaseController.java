package utouu.im.net.http;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;


import com.alibaba.fastjson.JSONObject;

public abstract class BaseController{
	public <T>T returnBean(HttpServletRequest request,Class<T> cls ) {
		String postData = returnString(request);
		return JSONObject.parseObject(postData, cls);
	}
	
	public String returnString(HttpServletRequest request){
		try {
			BufferedReader bufferReader = request.getReader();
			StringBuffer buffer = new StringBuffer();
			String line = " ";
			while ((line = bufferReader.readLine()) != null) {
			    buffer.append(line);
			}
			String postData = buffer.toString();
			return postData;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
