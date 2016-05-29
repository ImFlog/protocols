package fgarcia.test.protocols.client.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Flo on 14/04/2016.
 */
public class Person {

    String firstName;
    String lastName;
    String address;
    int age;
    List<String> moreInfo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(List<String> moreInfo) {
        this.moreInfo = moreInfo;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(firstName)
                .append(" ")
                .append(lastName)
                .append(" ")
                .append(address)
                .append(" ")
                .append(age)
                .append(" [")
                .append(moreInfo.stream().collect(Collectors.joining(", ")))
                .append("]")
                .toString();
    }
}
