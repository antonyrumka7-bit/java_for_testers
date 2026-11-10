package ru.stqa.mantis.manager;

import com.google.j2objc.annotations.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.Properties;


public class ApplicationManager {

    private WebDriver driver;

    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper JamesCliHelper;


    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;

    }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(string)) {
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            } else if ("edge".equals(string)) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknow browser %s", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if (JamesCliHelper == null) {
            JamesCliHelper = new JamesCliHelper(this);
        }
        return JamesCliHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);

    }
}
