package com.hello.typeconverter.converter;

import com.irostub.typeconverter.converter.IntegerToStringConverter;
import com.irostub.typeconverter.converter.IpPortToStringConverter;
import com.irostub.typeconverter.converter.StringToIntegerConverter;
import com.irostub.typeconverter.converter.StringToIpPortConverter;
import com.irostub.typeconverter.type.IpPort;
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

    @Test
    @DisplayName("String to IpPort")
    void stringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort convert = converter.convert("127.0.0.1:8080");
        assertThat(convert).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    @DisplayName("IpPort to String")
    void ipPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        String convert = converter.convert(new IpPort("127.0.0.1", 8080));
        assertThat(convert).isEqualTo("127.0.0.1:8080");
    }
}
