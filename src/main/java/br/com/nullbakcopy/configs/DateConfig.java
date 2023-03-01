package br.com.nullbakcopy.configs;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class DateConfig {

	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	public static LocalDateTimeSerializer LOCAL_DATE_TIME_SERIALIZER = 
			new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
	
	ObjectMapper objectMapper() {
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LOCAL_DATE_TIME_SERIALIZER);
		return new ObjectMapper().registerModule(javaTimeModule);
	}
}