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
	후술: 글로벌 검증기가 커스텀으로 등록된 경우 BeanValidator 가 전역으로 등록되지 않는 문제 때문에 주석처리
	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}
	*/
}
