package hello.itemservice.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {

    MessageCodesResolver resolver = new DefaultMessageCodesResolver();

    @Test
    @DisplayName("MessageCodesResolver 오브젝트 테스트")
    void messageCodesResolverObject() {
        String[] messageCodes = resolver.resolveMessageCodes("required", "item");
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    @DisplayName("MessageCodesResolver 필드 테스트")
    void messageCodesResolverField() {
        String[] messageCodes = resolver.resolveMessageCodes("required", "item","itemName", String.class);
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required");
    }
}
