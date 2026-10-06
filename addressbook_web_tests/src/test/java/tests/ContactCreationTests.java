package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

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
