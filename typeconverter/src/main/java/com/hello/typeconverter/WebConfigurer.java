package com.hello.typeconverter;

import com.hello.typeconverter.converter.IntegerToStringConverter;
import com.hello.typeconverter.converter.IpPortToStringConverter;
import com.hello.typeconverter.converter.StringToIntegerConverter;
import com.hello.typeconverter.converter.StringToIpPortConverter;
import com.hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        registry.addFormatter(new MyNumberFormatter());
    }
}
