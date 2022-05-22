package com.jakeseo.moyeoboa.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JavaBeanConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new CustomLocalDateTimeDeserializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(module);

        return objectMapper;
    }

    /**
     * ObjectMapper 에 이 Serializer 를 등록하면,
     * LocalDateTime 을 직렬화할 때 아래의 방식을 이용한다.
     */
    public static DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(formatter.format(value));
        }
    }

    /**
     * ObjectMapper 에 이 Deserializer 를 등록하면,
     * LocalDateTime 을 역직렬화할 때 아래의 방식을 이용한다.
     */
    static class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            ObjectCodec codec = p.getCodec();
            JsonNode node = codec.readTree(p);

            return LocalDateTime.parse(node.asText(), formatter);
        }
    }
}
