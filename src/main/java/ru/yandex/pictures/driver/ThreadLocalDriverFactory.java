package ru.yandex.pictures.driver;

import org.openqa.selenium.WebDriver;

public class ThreadLocalDriverFactory implements DriverFactory {

    private ThreadLocalDriverPool pool;

    public ThreadLocalDriverFactory() {
        pool = new ThreadLocalDriverPool();
    }

    @Override
    public WebDriver getDriver() {
        return pool.getDriver();
    }

    public void quit() {
        this.pool.quit();
    }

    public static ThreadLocalDriverFactory driverFactory() {
        return DriverFactoryHolder.instance;
    }

    private static class DriverFactoryHolder {
        private final static ThreadLocalDriverFactory instance = new ThreadLocalDriverFactory();
    }
}
