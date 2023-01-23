package com.sns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sns.common.FileManagerService;

@Configuration // 누락 시키지 말자 - config내의 들어있는 파일들은 모두 이 어노테이션을 붙여야 함
public class WebMvcCongig implements WebMvcConfigurer{
	
	// 여기 과정을 절대로 누락시키면 안된다.
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			
			registry
			.addResourceHandler("/images/**") // 웹 이미지 주소 http://localhost:8080/images/aaaa_16536/sun.png 이런식
			.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일 위치를 여기에 써주면 된다. 위 구문과 맵핑이 될 수 있도록.
		}
}
