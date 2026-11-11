package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test

    void canRegistrUser(String username) {
        var email = String.format("%s@localhost", username);
        // создать пользователя (адрес) на почтовом сервере (JamesHelper)
        // заполняем форму созданияи отправляем (браузер)
        // ждем почту (MailHelper)
        // извлекаем ссылку из письма
        // переходим по ссылке и завершаем регистрацию пользователя (браузер)
        // проверяем, что пользователь может залогиниться (HttpSessionHelper)
    }
}
