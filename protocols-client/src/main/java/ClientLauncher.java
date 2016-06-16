import fgarcia.test.protocols.client.web.AvroHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@ComponentScan("fgarcia.test.protocols.client")
@SpringBootApplication
public class ClientLauncher {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientLauncher.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(
                Arrays.asList(protobufHttpMessageConverter(), avroHttpMessageConverter()));
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
