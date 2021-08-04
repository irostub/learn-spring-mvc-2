package hello.itemservice.message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MessageSourceTest {

    private final MessageSource ms;

    @Autowired
    public MessageSourceTest(MessageSource ms) {
        this.ms = ms;
    }

    @Test
    @DisplayName("default 메시지 테스트")
    void helloMessage() {
        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @DisplayName("메시지 찾기 실패 테스트")
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("메시지 찾기 실패 후 기본 메시지 테스트")
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    @DisplayName("인자를 받을 수 있는 메시지 테스트")
    void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"spring"}, null);
        assertThat(message).isEqualTo("안녕 spring");
    }

    @Test
    @DisplayName("여러 인자를 받을 수 있는 메시지 테스트")
    void argumentsMessage() {
        String message = ms.getMessage("hello.names", new Object[]{"spring", "!"}, null);
        assertThat(message).isEqualTo("안녕 spring!");
    }

    @Test
    @DisplayName("default 국제화")
    void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    @DisplayName("en 국제화")
    void enLang() {
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
