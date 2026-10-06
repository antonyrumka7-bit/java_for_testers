package tests;

import model.ContactData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactCreationTests extends TestBase {

    @Test
    public void CanCreateContact() {
        app.contact().createContact(new ContactData("firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "homepage", "email", "email2", "email3"));
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
