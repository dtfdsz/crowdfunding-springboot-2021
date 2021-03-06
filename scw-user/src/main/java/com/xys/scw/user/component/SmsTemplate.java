package com.xys.scw.user.component;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.xys.scw.vo.resp.AppResponse;

@Component
public class SmsTemplate {

	@Value("${sms.host}")
	String host;
	@Value("${sms.path}")
    String path;
	@Value("${sms.method}")
    String method;
	@Value("${sms.appcode}")
    String appcode;
    
	public AppResponse<String> sendSms(Map<String, String> querys) {
	    
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
//	    Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("mobile", "");
//	    querys.put("param", "");
//	    querys.put("tpl_id", "");
	    Map<String, String> bodys = new HashMap<String, String>();


	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    	return AppResponse.ok(response.toString());
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return AppResponse.fail(null);
	    }
	}
}
