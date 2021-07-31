package com.hello.typeconverter.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

    @Test
    @DisplayName("String to Integer")
    void stringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer convertResult = converter.convert("12");
        assertThat(convertResult).isEqualTo(12);
    }

    @Test
    @DisplayName("Integer to String")
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String convertResult = converter.convert(12);
        assertThat(convertResult).isEqualTo("12");
    }
}
