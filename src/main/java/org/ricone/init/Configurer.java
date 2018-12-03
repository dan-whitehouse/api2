package org.ricone.init;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class Configurer implements WebMvcConfigurer {
    static {
        System.err.println("IM GETTING HERE");
    }



    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor permissionHandler = new PermissionHandler();
        HandlerInterceptor headerHandler = new HeaderHandler();
        HandlerInterceptor logHandler = new LogHandler();

        registry.addInterceptor(getAuthHandler());
        registry.addInterceptor(permissionHandler);
        registry.addInterceptor(headerHandler);
        registry.addInterceptor(logHandler);
    }*/

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
                .favorPathExtension(false)
                //.favorParameter(true).parameterName("mediaType")
                .ignoreAcceptHeader(false)
                .ignoreUnknownPathExtensions(true)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);

    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonConverter());
        converters.add(jaxbConverter());
        //TODO Still need to figure out how to handle a 406 (Not Acceptable)
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class, new StringSerializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(simpleModule);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapper);
        return converter;
    }

    @Bean
    public Jaxb2RootElementHttpMessageConverter jaxbConverter() {
        System.err.println("Loading: jaxbConverter");
        return new Jaxb2RootElementHttpMessageConverter();
    }
}
