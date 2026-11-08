package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("", "firstname")) {
//            for (var lastname : List.of("", "lastname")) {
//                for (var address : List.of("", "address")) {
//                    for (var photo : List.of("", randomFile("src/test/resources/images")))  {
//                        result.add(new ContactData()
//                                .withFirstname(firstname)
//                                .withLastname(lastname)
//                                .withAddress(address)
//                                .withphoto(photo));
//                    }
//                }
//            }
//        }
//
//        var json = "";
//        try (var reader = new FileReader("contacts.json");
//             var breader = new BufferedReader(reader)
//        ) {
//            var line = breader.readLine();
//            while (line != null) {
//                json = json + line;
//                line = breader.readLine();
//            }
//        }

        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>()
        {});
        result.addAll(value);
        return result;
    }

    public static Stream<ContactData> randomGroups() {
        Supplier<ContactData> randomContact = () -> (new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30)));
        return Stream.generate(randomContact).limit(3);

    }

@ParameterizedTest
@MethodSource("randomGroups")
public void CanCreateContacts(ContactData contact) {
    var oldContacts = app.hbm().getContactList();
    app.contact().createContact(contact);
    var newContacts = app.hbm().getContactList();
    var extraContacts = newContacts.stream().filter(g -> ! oldContacts.contains(g)).toList();
    var newId = extraContacts.get(0).id();

    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newId));
    Assertions.assertEquals(Set.copyOf(newContacts), Set.copyOf(expectedList));
}

public static List<ContactData> negativeContactProvider() {
    var result = new ArrayList<ContactData>(List.of(
            new ContactData().withFirstname("firstname'")));
    return result;
}

@ParameterizedTest
@MethodSource("negativeContactProvider")
public void canNotCreateContact(ContactData contact) {
    var oldContacts = app.contact().getList();
    app.contact().createContact(contact);
    var newContacts = app.contact().getList();
    Assertions.assertEquals(newContacts, oldContacts);
}

@Test
    void CanCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withphoto(randomFile("src/test/resources/images"));
    if (app.hbm().getGroupCount() == 0) {
        app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
    }
    var group = app.hbm().getGroupList().get(0);

    var oldRelated = app.hbm().getContactsInGroup(group);
        app.contact().create(contact, group);
    var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
}
}

