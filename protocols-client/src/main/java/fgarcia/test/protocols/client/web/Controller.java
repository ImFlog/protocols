package fgarcia.test.protocols.client.web;

import fgarcia.test.protocols.client.model.JsonPerson;
import fgarcia.test.protocols.client.services.PerformanceService;
import fgarcia.test.protocols.protobuf.ContentProtos;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

@RestController
public class Controller {
    private final static int HUNDRED = 100;

    @Inject
    PerformanceService perfService;

    @Inject
    RestTemplate restTemplate;

    @RequestMapping(value = "/jsonTest")
    public void startJsonTest() throws IOException {
        for (int i = 0; i < HUNDRED; i++) {
            perfService.startCounter(i);
            ParameterizedTypeReference<Map<Integer, JsonPerson>> typeRef = new ParameterizedTypeReference<Map<Integer, JsonPerson>>() {
            };
            ResponseEntity<Map<Integer, JsonPerson>> peopleList = restTemplate.exchange(
                    "http://localhost:8080/json", HttpMethod.GET, null, typeRef);
            for (Map.Entry<Integer, JsonPerson> entry : peopleList.getBody().entrySet()) {
                System.out.println("Got result : " + entry.toString());
            }
            perfService.setSize(i, peopleList.getHeaders().getContentLength());
            perfService.endCounter(i);
        }
        perfService.exportResults("jsonStats");
    }

    @RequestMapping(value = "/protobufTest")
    public void startProtoTest() throws IOException {
        for (int i = 0; i < HUNDRED; i++) {
            perfService.startCounter(i);
            ResponseEntity<ContentProtos.PeopleList> peopleList = restTemplate.exchange(
                    "http://localhost:8080/protobuf", HttpMethod.GET, null, ContentProtos.PeopleList.class);
            for (ContentProtos.MapEntry entry : peopleList.getBody().getEntryList()) {
                System.out.println("Got result : " + entry.toString());
            }
            perfService.setSize(i, peopleList.getHeaders().getContentLength());
            perfService.endCounter(i);
        }
        perfService.exportResults("protoStats");
    }
}