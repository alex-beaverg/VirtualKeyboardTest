package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.ExplicitCondition;

import java.lang.reflect.Array;
import java.util.Arrays;

public class VirtKeyHomePage extends page.AbstractPage {

    private static final String HOMEPAGE_URL = "https://virtual-keyboard-app.vercel.app/";
    private final Logger logger = LogManager.getRootLogger();

    public VirtKeyHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "textarea")
    private WebElement outputTextField;

    @Override
    public VirtKeyHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new ExplicitCondition(driver, outputTextField);
        logger.info("Was opened 'Virtual Keyboard' page with URL: [" + HOMEPAGE_URL + "]");
        return this;
    }

    public VirtKeyHomePage showKeyboard() {
        outputTextField.click();
        logger.info("Was showed keyboard on the screen");
        return this;
    }

    public String writeText(String str) {
        StringBuilder text = new StringBuilder();
        String keyLetter = "";
        for (char letter : str.toCharArray()) {
            if (letter == ' ') {
                keyLetter = "space";
            } else {
                keyLetter = String.valueOf(letter);
            }
            WebElement key = driver.findElement(By.id("keyboard__" + keyLetter));
            new ExplicitCondition(driver, key);
            key.click();
            text.append(letter);
        }
        logger.info("Was wrote text '" + str + "'");
        return text.toString();
    }
}
