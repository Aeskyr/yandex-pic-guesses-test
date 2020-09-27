package ru.yandex.pictures;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.stream.Collectors;

import static ru.yandex.pictures.driver.ThreadLocalDriverFactory.driverFactory;

public class Utils {

    public static String getPath(String fileName) {
        File target = new File(FileUtils.class.getClassLoader().getResource("pictures/" + fileName).getFile());
        return target.getAbsolutePath();
    }

    public static void switchTab() {
        WebDriver driver = driverFactory().getDriver();
        String otherTab = driver.getWindowHandles().stream()
                .filter(x -> !x.equals(driver.getWindowHandle()))
                .collect(Collectors.toList()).get(0);
        driver.switchTo().window(otherTab);
    }
}

