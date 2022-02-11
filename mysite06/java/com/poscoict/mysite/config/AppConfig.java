package com.poscoict.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.poscoict.config.app.DBConfig;
import com.poscoict.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.mysite.service","com.poscoict.mysite.repository","com.poscoict.mysite.aspect"})  //자바 자동 설정context:annotation-config 이부분
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

}
