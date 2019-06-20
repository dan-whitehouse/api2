package org.ricone.init;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.ricone.api.oneroster.model.serializer.BooleanStringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
            builder.serializers(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat).withZone(ZoneId.of("America/New_York"))));
            builder.serializers(new BooleanStringSerializer(Boolean.class));
            //builder.featuresToDisable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            //builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);
            //builder.defaultUseWrapper(false);
            //builder.createXmlMapper(true);
            builder.featuresToEnable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        };
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //Override default paging settings, so that an empty paging request is 'unpaged', instead of (0,10)
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setOneIndexedParameters(true);
        resolver.setFallbackPageable(Pageable.unpaged());
        argumentResolvers.add(resolver);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
            .favorPathExtension(true)
            //.favorParameter(true).parameterName("mediaType")
            .ignoreAcceptHeader(false)
            .ignoreUnknownPathExtensions(true)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML)
            .useRegisteredExtensionsOnly(true);
    }

    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor logInterceptor = new LogInterceptor();
        registry.addInterceptor(logInterceptor);
    }*/
}
