package com.xys.scw.project.component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@ToString
@Data
@Slf4j
public class OssTemplate {
	
	
			String endpoint;
			
			String accessKeyId;
			String accessKeySecret;
			String bucket;

	public String upload(String filename,InputStream inputStream) {
//		// yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
//		String endpoint = "http://oss-cn-shanghai.aliyuncs.com";

		log.debug("endpoint={}",endpoint);
		log.debug("accessKeyId={}",accessKeyId);
		log.debug("accessKeySecret={}",accessKeySecret);
		log.debug("bucket={}",bucket);
		
		try {
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build("http://"+endpoint, accessKeyId, accessKeySecret);

		
//			InputStream inputStream=new FileInputStream("C:\\Users\\23910\\Pictures\\booooom\\McPhail1.jpg");
			ossClient.putObject(bucket,"pic/"+filename,inputStream);
			ossClient.shutdown();  
			
			String filepath="https://"+bucket+"."+endpoint+"/pic/"+filename;
			log.debug("文件上传成功",filepath);
			return filepath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("文件上传失败",filename);
			return null;
		}
		
	}
}
