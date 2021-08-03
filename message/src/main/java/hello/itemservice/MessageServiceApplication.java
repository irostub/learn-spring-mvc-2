package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

	/* spring boot 를 사용할 땐 자동으로 bean 을 등록함으로 굳이 수동으로 넣을 필요없다.
	특별하게 설정할 일이 필요할 경우 properties 에서 설정할 수 있다.
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages", "errors");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	*/
}
