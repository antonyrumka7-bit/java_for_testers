package ru.stqa.mantis.manager;
import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void start(String username, String email){
            manager.driver().get(manager.property("web.baseUrl"));
            click(By.cssSelector("a[href='signup_page.php']"));
            type(By.name("username"), username);
            type(By.name("email"), email);
            click(By.cssSelector("input[type='submit']"));
        }

        public void finish(String url, String username, String password){
            manager.driver().get(url);
            type(By.name("realname"), username);
            type(By.name("password"), password);
            type(By.name("password_confirm"), password);
            click(By.cssSelector("button[type='submit']"));
        }
    }
