package com.hello.typeconverter.converter;

import com.irostub.typeconverter.converter.IntegerToStringConverter;
import com.irostub.typeconverter.converter.IpPortToStringConverter;
import com.irostub.typeconverter.converter.StringToIntegerConverter;
import com.irostub.typeconverter.converter.StringToIpPortConverter;
import com.irostub.typeconverter.type.IpPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class ConversionServiceTest {

    @Test
    @DisplayName("conversionService Test")
    void conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());

        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
        assertThat(conversionService.convert(new IpPort("127.0.0.1", 8080), String.class)).isEqualTo("127.0.0.1:8080");
        assertThat(conversionService.convert("127.0.0.1:8080", IpPort.class)).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
