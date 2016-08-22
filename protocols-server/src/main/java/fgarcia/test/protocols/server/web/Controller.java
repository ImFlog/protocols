package fgarcia.test.protocols.server.web;

import fgarcia.test.protocols.avro.PeopleList;
import fgarcia.test.protocols.protobuf.ContentProtos;
import fgarcia.test.protocols.server.model.JsonPerson;
import fgarcia.test.protocols.server.service.ObjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    private ObjectBuilder objectBuilder;

    @Autowired
    public Controller(ObjectBuilder objectBuilder) {
        this.objectBuilder = objectBuilder;
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, JsonPerson> getJsonPeople(@RequestParam(required = false) Integer size) {
        return objectBuilder.jsonBuilder(size);
    }

    @RequestMapping(value = "/protobuf", method = RequestMethod.GET)
    public ContentProtos.PeopleList getProtoPeople(@RequestParam(required = false) Integer size) {
        return objectBuilder.protoBuilder(size);
    }

    @RequestMapping(value = "/avro", method = RequestMethod.GET)
    public PeopleList getAvroPeople(@RequestParam(required = false) Integer size) {
        return objectBuilder.avroBuilder(size);
    }
}
