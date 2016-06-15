package fgarcia.test.protocols.server.service;

import fgarcia.test.protocols.avro.PeopleList;
import fgarcia.test.protocols.avro.Person;
import fgarcia.test.protocols.protobuf.ContentProtos;
import fgarcia.test.protocols.server.model.JsonPerson;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ObjectBuilder {

    public static int AMOUNT = 1000;

    public Map<String, JsonPerson> jsonBuilder() {
        Map<String, JsonPerson> peopleList = new HashMap<>();
        for (int i = 0; i < AMOUNT; i++) {
            peopleList.put(String.valueOf(i), createJsonPerson(i));
        }
        return peopleList;
    }

    private JsonPerson createJsonPerson(int i) {
        JsonPerson p = new JsonPerson();
        p.setFirstName("Foo " + i);
        p.setLastName("Bar " + i);
        p.setAge(i);
        p.setAddress(i + " bar street Paris");
        p.setMoreInfo(Arrays.asList("foo", "bar", "babar", "foofoo"));
        return p;
    }

    public ContentProtos.PeopleList protoBuilder() {
        ContentProtos.PeopleList.Builder peopleList = ContentProtos.PeopleList.newBuilder();
        for (int i = 0; i < AMOUNT; i++) {
            peopleList.addEntry(ContentProtos.MapEntry.newBuilder()
                    .setKey(String.valueOf(i))
                    .setValue(createProtoPerson(i)));
        }
        return peopleList.build();
    }

    private ContentProtos.Person createProtoPerson(int i) {
        return ContentProtos.Person.newBuilder()
                .setFirstName("Foo " + i)
                .setLastName("Bar " + i)
                .setAge(i)
                .setAddress(i + " bar street Paris")
                .addAllMoreInfo(Arrays.asList("foo", "bar", "babar", "foofoo"))
                .build();
    }

    public PeopleList avroBuilder() {
        PeopleList peopleList = new PeopleList();
        peopleList.setItems(new HashMap<>());
        for (int i = 0; i < AMOUNT; i++) {
            peopleList.getItems().put(
                    String.valueOf(i),
                    createAvroPerson(i));
        }
        return peopleList;
    }

    private Person createAvroPerson(int i) {
        return Person.newBuilder()
                .setFirstName("Foo " + i)
                .setLastName("Bar " + i)
                .setAge(i)
                .setAddress(i + " bar street Paris")
                .setMoreInfo(Arrays.asList("foo", "bar", "babar", "foofoo"))
                .build();
    }
}
