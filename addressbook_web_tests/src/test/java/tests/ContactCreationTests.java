package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static tests.TestBase.app;

public class ContactCreationTests extends TestBase {

    public static List<GroupData> contactProvider() {
        var result = new ArrayList<GroupData>();
        for (var firstname : List.of("", "firstname")) {
            for (var middlename : List.of("", "middlename")) {
                for (var lastname : List.of("", "lastname")) {
                    for (var nickname : List.of("", "nickname")) {
                        for (var title : List.of("", "title")) {
                            for (var company : List.of("", "company")) {
                                for (var address : List.of("", "address")) {
                                    for (var home : List.of("", "home")) {
                                        for (var mobile : List.of("", "mobile")) {
                                            for (var work : List.of("", "work")) {
                                                for (var email : List.of("", "email")) {
                                                    for (var email2 : List.of("", "email2")) {
                                                        for (var email3 : List.of("", "email3")) {
                                                            result.add(new GroupData(firstname, middlename, lastname, nickname, title, company, address, home, mobile, work, email, email2, email3));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


 for (int i = 0; i < 5; i++) {
        result.add(new ContactData(randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
 }


    @Test
    public void CanCreateContact() {
        app.contact().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "email", "email2", "email3"));
    }
    @Test
    public void CanCreationContactWithEmptyName() {
        app.contact().createContact(new ContactData());
    }

    @Test
    public void CanCreateContactWithNameOnly() {
        var emptyContact = new ContactData();
        var contactWithName = emptyContact.withFirstname("some name");
        app.contact().createContact(new ContactData().withFirstname("some name"));
    }
}
