package com.ssafy.spring.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.spring.interceptor.SessionInterceptor;

import io.swagger.models.HttpMethod;

@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.ssafy.spring.*.model.mapper" })
public class WebMvcConfiguration implements WebMvcConfigurer {

//	Interceptor에서 처리할 url 리스트를 설정 
	private final List<String> patterns = Arrays.asList("/admin");

	@Autowired
	private SessionInterceptor sessionInterceptor;

	private final String uploadFilePath;

	public WebMvcConfiguration(@Value("${file.path.upload-files}") String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

//	RestAPI를 위한 CrossOrigine 설정 
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
//			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
				.maxAge(1800);
	}
	
//	interceptor를 위한 설정 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionInterceptor).addPathPatterns(patterns);
	}

	
//	fileupload를 위한   요청 경로와 실제 경로 mapping 설정 
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/upload/file/**").addResourceLocations("file:///" + uploadFilePath + "/")
//				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
//	}

}
