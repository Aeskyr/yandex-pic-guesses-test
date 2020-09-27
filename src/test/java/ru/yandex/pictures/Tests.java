package ru.yandex.pictures;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItems;
import static ru.yandex.pictures.Utils.getPath;
import static ru.yandex.pictures.Utils.switchTab;
import static ru.yandex.pictures.driver.ThreadLocalDriverFactory.driverFactory;
import static ru.yandex.pictures.pages.MainPage.mainPage;
import static ru.yandex.pictures.pages.PicSearchResultsPage.picSearchResultsPage;
import static ru.yandex.pictures.pages.PicturesPage.picturesPage;

public class Tests {

    private WebDriver driver;
    private static final String url = "http://yandex.ru/";

    @BeforeMethod
    public void createDriver() {
        driver = driverFactory().getDriver();
    }

    @DataProvider(name = "Картинки и тэги")
    public Object[][] dataProvider() {
        return new Object[][]{
                {"MasterOfMankind.png", "Император"},
                {"Cheems.jpg", "Собака"},
                {"RickRoll.gif", "get stick bugged"},
        };
    }

    @Test(dataProvider = "Картинки и тэги")
    public void yandexPicGuessesTest(String path, String tag) {
        driver.get(url);
        mainPage().picturesBlockButton.click();
        switchTab();
        picturesPage().searchByPicButton.click();
        picturesPage().uploadPicButton.sendKeys(getPath(path));
        picSearchResultsPage().waitForPics();
        assertThat(picSearchResultsPage().getTags(), hasItems(containsString(tag)));
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driverFactory().quit();
    }
}


