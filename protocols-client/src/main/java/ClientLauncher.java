import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@ComponentScan("fgarcia.test.protocols.client")
@SpringBootApplication
public class ClientLauncher {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientLauncher.class, args);
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(hmc);
        return restTemplate;
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
}
