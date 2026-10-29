package ru.stqa.addressbook.manager;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends  HelperBase {
    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }

public void openContactPage() {
     if (!manager.isElementPresent(By.name("firstname"))) {
         click(By.linkText("add new"));
     }
}

public void create(ContactData contact, GroupData group) {
    openContactPage();
    initContactCreation();
    fillContactForm(contact);
    selectGroup(group);
    submitContactCreation();
    returnToHomePage();
}

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("firstname"))) {
            click(By.linkText("home"));
        }
}

public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(ContactData contact) {
         openHomePage();
         selectContact(contact);
         removeSelectedContacts();
         returnToHomePage();
    }
        public void modifyContact(ContactData contact, ContactData modifiedContact) {
            openHomePage();
            initContactModification(contact);
            fillContactForm(modifiedContact);
            submitContactModification();
            returnToHomePage();
}
    private void submitContactCreation() {

        click(By.xpath("(//input[@name=\'submit\'])[2]"));
    }
    private void initContactCreation() {

        click(By.name("firstname"));
    }

    private void removeSelectedContacts() {

        click(By.name("delete"));
    }

    private void returnToHomePage() {

        click(By.linkText("home page"));
    }

    private void submitContactModification() {

        click(By.name("update"));
    }
    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), contact.nickname());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        if (contact.photo() != null && !contact.photo().isEmpty()) {
            attach(By.name("photo"), contact.photo());
        }

    }
    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var cells = tr.findElements(By.tagName("td"));
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var firstname = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname));
        }
        return contacts;
    }

    public ContactData searchContactForAddInGroup(List<ContactData> oldContacts, List<ContactData> oldRelated) {
        for (ContactData contact : oldContacts) {
            if (!oldRelated.contains(contact)) {
                return contact;
            }
        }
        return  null;
    }


    public void addContact(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
        click(By.name("add"));
        openHomePage();
    }

    public void removeContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupFilter(group.id());
        selectContact(contact);
        click(By.name("remove"));
        click(By.linkText("home"));
        selectGroupFilter("");
    }

    private void selectGroupFilter(String value) {
        new Select(manager.driver.findElement(By.cssSelector("select[name='group']"))).selectByValue(value);
    }
}
