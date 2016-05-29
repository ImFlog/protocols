package fgarcia.test.protocols.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@ComponentScan("fgarcia.test.protocols.server")
@SpringBootApplication
public class ServerLauncher {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerLauncher.class, args);
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}