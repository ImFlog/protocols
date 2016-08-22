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
/**
 * Service responsible for object creation.
 * Handle Json, Protobuf and Avro creation.
 */
public class ObjectBuilder {

    // Amount of created objects
    private static final int AMOUNT = 1000;

    /**
     * Build json people list.
     */
    public Map<String, JsonPerson> jsonBuilder(Integer size) {
        Map<String, JsonPerson> peopleList = new HashMap<>();
        for (int i = 0; i < (size != null? size: AMOUNT); i++) {
            peopleList.put(String.valueOf(i), createJsonPerson(i));
        }
        return peopleList;
    }

    /**
     * Create a single Json Person.
     */
    private JsonPerson createJsonPerson(int i) {
        JsonPerson p = new JsonPerson();
        p.setFirstName("Foo " + i);
        p.setLastName("Bar " + i);
        p.setAge(i);
        p.setAddress(i + " bar street Paris");
        p.setMoreInfo(Arrays.asList("foo", "bar", "babar", "foofoo"));
        return p;
    }

    /**
     * Build protobuf people list.
     */
    public ContentProtos.PeopleList protoBuilder(Integer size) {
        ContentProtos.PeopleList.Builder peopleList = ContentProtos.PeopleList.newBuilder();
        for (int i = 0; i < (size != null? size: AMOUNT); i++) {
            peopleList.addEntry(ContentProtos.MapEntry.newBuilder()
                    .setKey(String.valueOf(i))
                    .setValue(createProtoPerson(i)));
        }
        return peopleList.build();
    }

    /**
     * Create a single protobuf Person.
     */
    private ContentProtos.Person createProtoPerson(int i) {
        return ContentProtos.Person.newBuilder()
                .setFirstName("Foo " + i)
                .setLastName("Bar " + i)
                .setAge(i)
                .setAddress(i + " bar street Paris")
                .addAllMoreInfo(Arrays.asList("foo", "bar", "babar", "foofoo"))
                .build();
    }

    /**
     * Build avro people list.
     */
    public PeopleList avroBuilder(Integer size) {
        PeopleList peopleList = new PeopleList();
        peopleList.setItems(new HashMap<>());
        for (int i = 0; i < (size != null? size: AMOUNT); i++) {
            peopleList.getItems().put(
                    String.valueOf(i),
                    createAvroPerson(i));
        }
        return peopleList;
    }

    /**
     * Create a single avro Person.
     */
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
