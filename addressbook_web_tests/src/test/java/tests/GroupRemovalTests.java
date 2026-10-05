package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!TestBase.app.groups().isGroupPresent()) {
            TestBase.app.groups().createGroup(new GroupData("", "", ""));
        }
        TestBase.app.groups().removeGroup();
    }

}
