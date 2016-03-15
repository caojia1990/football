package com.eastng.football.main;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动类
 * @author caojia
 */
public class FootballApplication {
	
	public static void main(String[] args) throws IOException {
		System.out.println("===================service  start ...==================");
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        classPathXmlApplicationContext.start();
        System.out.println("===================service  start  complete!==================");
        System.in.read();
        classPathXmlApplicationContext.close();
	}
}
