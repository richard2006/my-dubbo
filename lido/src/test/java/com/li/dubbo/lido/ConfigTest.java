/**
 * 
 */
package com.li.dubbo.lido;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.li.dubbo.lido.config.People;

import junit.framework.TestCase;

/**
 * @author lipeng
 *
 * 2016年5月12日
 */
public class ConfigTest extends TestCase{
	
	public static void testConfig(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");  
		People p = (People)ctx.getBean("person");  
		System.out.println(p.getName());  
		System.out.println(p.getAge()); 
	}
	public static void testMini(){
		ConcurrentMap<String, String> hs = new ConcurrentHashMap<>();
		String d = hs.putIfAbsent("1", "aaa");
		System.out.println(d);
		
		System.out.println(hs.putIfAbsent("1", "bbb"));
	}
}
