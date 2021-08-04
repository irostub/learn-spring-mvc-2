package com.hello.typeconverter.fomatter;

import com.irostub.typeconverter.formatter.MyNumberFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class MyNumberFormatterTest {

    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    @DisplayName("포맷팅 문자에서 숫자로")
    void parse() throws ParseException {
        Number parse = formatter.parse("1,000", Locale.KOREA);
        assertThat(parse).isEqualTo(1000L);
    }

    @Test
    @DisplayName("숫자에서 포맷팅 문자로")
    void print() {
        String print = formatter.print(1000L, Locale.KOREA);
        assertThat(print).isEqualTo("1,000");
    }
}
