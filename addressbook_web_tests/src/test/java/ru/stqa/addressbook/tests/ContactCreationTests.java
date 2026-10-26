package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    public static List<ContactData> singleRandomGroup() {
        return List.of(new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30)));

    }

@ParameterizedTest
@MethodSource("singleRandomGroup")
public void CanCreateContacts(ContactData contact) {
    var oldContacts = app.jdbc().getContactList();
    app.contact().createContact(contact);
    var newContacts = app.jdbc().getContactList();
    Comparator<ContactData> compareById = (o1, o2) -> {
        return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    var maxId = newContacts.get(newContacts.size() - 1).id();

    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(maxId));
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);
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
}

