package com.poscoict.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:com/poscoict/mysite/config/app/jdbc.properties")  //이걸 읽어서객체에다 저장
public class DBConfig {
	
	
	@Autowired
	private Environment env;

	//Connection Pool DataSource
	@Bean
	public DataSource dataSource() {
		
		//내부에서는 실제로 생성하는 변수로 하는게 좋다.
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));  //xml이 아니기 때문에 amp; escpe 해줄 필요 없다.
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		//튜닝값
		dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));  // "" 이거는 string 값 리턴하는 것
		dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));  
		// 최대로 100까지 들어오게 Integer로 만들어 두었다.
		//아무리 많이 요청 들어와도 100개이사 만들지 마라
		
		
		return dataSource;
	}
	
	
}
