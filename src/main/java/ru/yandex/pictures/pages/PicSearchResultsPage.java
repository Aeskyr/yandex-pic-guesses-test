package ru.yandex.pictures.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;
import static ru.yandex.pictures.driver.ThreadLocalDriverFactory.driverFactory;

public class PicSearchResultsPage {

    @Name("Плашка догадки яндекса")
    @FindBy(css = "[class*='Tags-Item']")
    public List<YandexGuessTag> yandexGuesses;

    @Name("Кажется, на картинке")
    @FindBy(xpath = "(.//*[contains(*,'Кажется, на картинке')])[5]")
    public HtmlElement onPic;

    public List<String> getTags() {
        return yandexGuesses.stream()
                .map(e -> e.text.getText())
                .collect(Collectors.toList());
    }

    public void waitForPics() {
        new FluentWait<>(onPic)
                .withTimeout(ofSeconds(15))
                .until(HtmlElement::exists);
    }

    public static class YandexGuessTag extends HtmlElement {

        @Name("Текст догадки яндекса")
        @FindBy(css = "[class*='Button2-Text']")
        public HtmlElement text;
    }

    public static PicSearchResultsPage picSearchResultsPage() {
        return new PicSearchResultsPage();
    }

    private PicSearchResultsPage() {
        HtmlElementLoader.populate(this, driverFactory().getDriver());
    }
}
