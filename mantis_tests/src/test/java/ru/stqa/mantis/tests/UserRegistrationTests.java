package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;


public class UserRegistrationTests extends TestBase {

    @Test

    void canRegistrUser() {
        var username = CommonFunctions.randomString(10);
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesCli().addUser(email, password);

        app.registration().start(username, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var url = extractUrl(messages.get(0).content());

        app.registration().finish(url, username, password);

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    private static String extractUrl(String text) {
        var matcher = Pattern.compile("http://\\S*").matcher(text);
        if (matcher.find()) {
            return text.substring(matcher.start(), matcher.end());
        }
        throw new RuntimeException("No URL in mail: " + text);

    }
}
