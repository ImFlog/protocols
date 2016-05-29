package com.test.protocols.server.service;

import com.test.protocols.server.model.JsonPerson;
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
}
