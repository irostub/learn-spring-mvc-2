package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemServiceApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	/* ItemValidator 를 전역으로 사용하고 싶을 때
	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}
	*/
}
