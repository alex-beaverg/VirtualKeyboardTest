package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.MapCreator;
import waits.ExplicitCondition;

import java.util.HashMap;

public class VirtKeyHomePage extends page.AbstractPage {

    private static final String HOMEPAGE_URL = "https://virtual-keyboard-app.vercel.app/";
    private final Logger logger = LogManager.getRootLogger();
    private final HashMap<Character, Character> EnShiftMap = new MapCreator().createEnglishShiftMap();
    private final HashMap<Character, Character> RuShiftMap = new MapCreator().createRussianShiftMap();
    private final HashMap<Character, Character> EnRuMap = new MapCreator().createEnRuMap();

    public VirtKeyHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "use-keyboard-input")
    private WebElement outputTextField;

    @Override
    public VirtKeyHomePage openPage() {
        driver.get(HOMEPAGE_URL);
        new ExplicitCondition(driver, outputTextField);
        logger.info("Was opened 'Virtual Keyboard' page with URL: [" + HOMEPAGE_URL + "]");
        return this;
    }

    public VirtKeyHomePage showKeyboard() {
        new ExplicitCondition(driver, outputTextField);
        outputTextField.click();
        logger.info("Was showed keyboard on the screen");
        return this;
    }

    public VirtKeyHomePage maximizeTextArea() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementsByClassName('use-keyboard-input')[0].style.width = '100%';");
        executor.executeScript("document.getElementsByClassName('use-keyboard-input')[0]" +
                ".style.height = '450px';");
        executor.executeScript("document.getElementsByClassName('use-keyboard-input')[0]" +
                ".style.background = 'rgb(254, 255, 204)';");
        return this;
    }

    public VirtKeyHomePage writeText(String str) {
        String keyLetter;
        boolean isShift = false;
        boolean isRussian = false;

        for (char letter : str.toCharArray()) {
            if (letter == ' ') {
                keyLetter = "space";
            } else if (letter == '\n') {
                keyLetter = "enter";
            } else if (letter == '№') {
                if (!isShift) {
                    clickShiftWithJSExecutor();
                    isShift = true;
                }
                if (!isRussian) {
                    clickLangWIthJSExecutor();
                    isRussian = true;
                }
                keyLetter = "3";
            } else if (EnShiftMap.containsKey(letter)) {
                if (!isShift) {
                    clickShiftWithJSExecutor();
                    isShift = true;
                }
                if (isRussian) {
                    clickLangWIthJSExecutor();
                    isRussian = false;
                }
                keyLetter = String.valueOf(EnShiftMap.get(letter));
            } else if (RuShiftMap.containsKey(letter)) {
                if (!isShift) {
                    clickShiftWithJSExecutor();
                    isShift = true;
                }
                if (!isRussian) {
                    clickLangWIthJSExecutor();
                    isRussian = true;
                }
                keyLetter = String.valueOf(EnRuMap.get(RuShiftMap.get(letter)));
            } else {
                if (isShift) {
                    clickShiftWithJSExecutor();
                    isShift = false;
                }
                if (EnShiftMap.containsValue(letter)) {
                    if (isRussian) {
                        clickLangWIthJSExecutor();
                        isRussian = false;
                    }
                    keyLetter = String.valueOf(letter);
                } else {
                    if (!isRussian) {
                        clickLangWIthJSExecutor();
                        isRussian = true;
                    }
                    keyLetter = String.valueOf(EnRuMap.get(letter));
                }
            }
            try {
                WebElement key = driver.findElement(By.id("keyboard__" + keyLetter));
                new ExplicitCondition(driver, key);
                key.click();
            } catch (ElementClickInterceptedException e) {
                clickWIthJSExecutor("keyboard__" + keyLetter);
            }
        }
        if (isShift) {
            clickShiftWithJSExecutor();
        }
        if (isRussian) {
            clickLangWIthJSExecutor();
        }
        logger.info("Was wrote text '" + driver.findElement(By.tagName("textarea")).getAttribute("value") + "'");
        return this;
    }

    private void clickShiftWithJSExecutor() {
        logger.info("Was clicked key 'Shift'");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementById('keyboard__shift').click();");
    }

    private void clickLangWIthJSExecutor() {
        logger.info("Was clicked key 'Language'");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementById('keyboard__EN').click();");
    }

    private void clickWIthJSExecutor(String id) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementById('" + id + "').click();");
    }

    public VirtKeyHomePage clickArrowLeft(int number) {
        WebElement left = driver.findElement(By.id("keyboard__arrowleft"));
        for (int i = 0; i < number; i++) {
            left.click();
        }
        return this;
    }

    public VirtKeyHomePage clickArrowRight(int number) {
        WebElement right = driver.findElement(By.id("keyboard__arrowright"));
        for (int i = 0; i < number; i++) {
            right.click();
        }
        return this;
    }

    public VirtKeyHomePage clickBackspace(int number) {
        WebElement back = driver.findElement(By.id("keyboard__backspace"));
        for (int i = 0; i < number; i++) {
            back.click();
        }
        return this;
    }
}
