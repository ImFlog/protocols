package fgarcia.test.protocols.server.web;

import fgarcia.test.protocols.avro.PeopleList;
import fgarcia.test.protocols.protobuf.ContentProtos;
import fgarcia.test.protocols.server.model.JsonPerson;
import fgarcia.test.protocols.server.service.ObjectBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Map;

@RestController
public class Controller {

    @Inject
    ObjectBuilder objectBuilder;

    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, JsonPerson> getJsonPeople() {
        return objectBuilder.jsonBuilder();
    }

    @RequestMapping(value = "/protobuf", method = RequestMethod.GET)
    public ContentProtos.PeopleList getProtoPeople() {
        return objectBuilder.protoBuilder();
    }

    @RequestMapping(value = "/avro", method = RequestMethod.GET)
    public PeopleList getAvroPeople() {
        return objectBuilder.avroBuilder();
    }
}
