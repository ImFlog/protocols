package fgarcia.test.protocols.server;

import fgarcia.test.protocols.server.web.AvroHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@ComponentScan("fgarcia.test.protocols.server")
@SpringBootApplication
public class ServerLauncher {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerLauncher.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(protobufHttpMessageConverter());
        restTemplate.getMessageConverters().add(avroHttpMessageConverter());
        return restTemplate;
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    AvroHttpMessageConverter avroHttpMessageConverter() {
        return new AvroHttpMessageConverter();
    }
}