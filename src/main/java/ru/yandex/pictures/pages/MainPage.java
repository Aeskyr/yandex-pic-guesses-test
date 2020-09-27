package ru.yandex.pictures.pages;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static ru.yandex.pictures.driver.ThreadLocalDriverFactory.driverFactory;

public class MainPage {

    @Name("Кнопка поиска по картинкам")
    @FindBy(xpath = ".//div[text()='Картинки']")
    public HtmlElement picturesBlockButton;

    public static MainPage mainPage() {
        return new MainPage();
    }

    private MainPage() {
        HtmlElementLoader.populate(this, driverFactory().getDriver());
    }
}
