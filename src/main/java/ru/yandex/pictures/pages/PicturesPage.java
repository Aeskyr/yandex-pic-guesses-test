package ru.yandex.pictures.pages;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import static ru.yandex.pictures.driver.ThreadLocalDriverFactory.driverFactory;

public class PicturesPage {

    @Name("Кнопка поиска по картинкам")
    @FindBy(css = "[aria-label='Поиск по картинке']")
    public HtmlElement searchByPicButton;

    @Name("Кнопка загрузки картинки")
    @FindBy(name = "upfile")
    public HtmlElement uploadPicButton;

    public static PicturesPage picturesPage() {
        return new PicturesPage();
    }

    private PicturesPage() {
        HtmlElementLoader.populate(this, driverFactory().getDriver());
    }
}
