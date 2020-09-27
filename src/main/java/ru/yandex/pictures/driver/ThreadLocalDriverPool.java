package ru.yandex.pictures.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ThreadLocalDriverPool implements DriverFactory {

    private ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    @Override
    public WebDriver getDriver() {
        if (driverPool.get() == null) {
            WebDriverManager.chromedriver().setup();
            driverPool.set(new ChromeDriver());
        }
        return driverPool.get();
    }

    public void quit() {
        try {
            if (driverPool.get() != null) {
                driverPool.get().quit();
            }
        } finally {
            driverPool.remove();
        }
    }
}
