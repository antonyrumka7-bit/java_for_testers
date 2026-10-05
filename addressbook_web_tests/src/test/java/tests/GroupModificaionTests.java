package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificaionTests extends TestBase {

    @Test
    void canModifayGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("", "", ""));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
