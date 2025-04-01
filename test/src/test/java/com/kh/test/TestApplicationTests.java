package com.kh.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {
	/*
	 * AutoConfiguration(자동 구성)
	 * 
	 * 스프링부트의 핵심 기능
	 * 애플리케이션의 클래스패스에 존재하는 라이브러리 및 설정을 기반으로
	 * Bean을 자도으로 구성해줌
	 * 
	 * @EnableAutoConfiguration
	 * -> SpringBoot의 자동구성을 활성화 해주는 애노테이션
	 * => @SpringBootApplication 내부에 포함되어있어서 작성할 일이 거의 없음
	 * 
	 * 동작순서
	 * 
	 * 애플리케이션 시작 -> @SpringBootApplicaion 애노테이션이 붙은 클래스의 
	 * 					main메서드가 호출됨
	 * 자동구성 활성화 -> @EnableAutoConfiguration을 통해 자동구성을 활성화
	 * 
	 * @EnableAutoConfiguration
	 * @ComponentScan
	 * @Configuration
	 * 
	 */
	@Test
	void contextLoads() {
	}

}
