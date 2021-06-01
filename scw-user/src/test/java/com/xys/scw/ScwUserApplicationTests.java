package com.xys.scw;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;


@SpringBootTest
class ScwUserApplicationTests {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void test01() throws SQLException {
		Connection conn=dataSource.getConnection();
		
		
		System.out.println("conn连接："+conn);
		
		conn.close();
	}
	
	@Test
	public void test02() {
		
		System.out.println("this is my test02............");
		System.out.println("this is my test02............");
		System.out.println("this is my test02............");
		System.out.println("this is my test02............");
		System.out.println("this is my test02............");
	}
	
	@Test
	public void test03() {
		System.out.println("this is my test03............");
		stringRedisTemplate.opsForValue().set("key111", "value111");
	}
	
	@Test
	public void test04() {
		System.out.println("this is my test04............");
		System.out.println("this is my test04............");
		System.out.println("this is my test04............");
		System.out.println("this is my test04............");
		String string = stringRedisTemplate.opsForValue().get("key111");
		System.out.println(string);
		System.out.println("this is my test04............");
		System.out.println("this is my test04............");
		System.out.println("this is my test04............");
		System.out.println("this is my test04............");
		System.out.println("this is my test04............");
	}
	
		
	

}
