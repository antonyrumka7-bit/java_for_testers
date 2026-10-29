package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "email", "email2", "email3", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstname("modified firstname");
        app.contact().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }

    @Test
    void canAddContactInGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "email", "email2", "email3", ""));
        }
        var oldContacts = app.hbm().getContactList();
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        ContactData contactForAddInGroup = app.contact().searchContactForAddInGroup(oldContacts, oldRelated);
        if (contactForAddInGroup == null) {
            app.hbm().createContact(new ContactData("", "firstname", "middlename", "lastname", "nickname", "title", "company", "address", "home", "mobile", "work", "email", "email2", "email3", ""));
            var newContactList = app.hbm().getContactList();
            contactForAddInGroup = app.contact().searchContactForAddInGroup(newContactList, oldRelated);
        }
        app.contact().addContact(contactForAddInGroup, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contactForAddInGroup);
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newRelated));
    }
}



