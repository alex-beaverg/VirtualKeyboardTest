package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.ExplicitCondition;

import java.util.HashMap;

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
        String keyLetter;
        boolean isShift = false;
        for (char letter : str.toCharArray()) {
            if (isShift) {
                clickShiftWithJSExecutor();
                isShift = false;
            }
            if (letter == ' ') {
                keyLetter = "space";
            } else if (crateEnMap().containsKey(letter)) {
                WebElement shift = driver.findElement(By.id("keyboard__shift"));
                new ExplicitCondition(driver, shift);
                clickShiftWithJSExecutor();
                keyLetter = String.valueOf(crateEnMap().get(letter));
                isShift = true;
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

    private void clickShiftWithJSExecutor() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementById('keyboard__shift').click();");
    }

    private HashMap<Character, Character> crateEnMap() {
        HashMap<Character, Character> symbols = new HashMap<>();
        symbols.put('~', '`');
        symbols.put('!', '1');
        symbols.put('@', '2');
        symbols.put('#', '3');
        symbols.put('$', '4');
        symbols.put('%', '5');
        symbols.put('^', '6');
        symbols.put('&', '7');
        symbols.put('*', '8');
        symbols.put('(', '9');
        symbols.put(')', '0');
        symbols.put('_', '-');
        symbols.put('+', '=');
        symbols.put('{', '[');
        symbols.put('}', ']');
        symbols.put(':', ';');
        symbols.put('"', '\'');
        symbols.put('|', '\\');
        symbols.put('<', ',');
        symbols.put('>', '.');
        symbols.put('?', '/');
        symbols.put('Q', 'q');
        symbols.put('W', 'w');
        symbols.put('E', 'e');
        symbols.put('R', 'r');
        symbols.put('T', 't');
        symbols.put('Y', 'y');
        symbols.put('U', 'u');
        symbols.put('I', 'i');
        symbols.put('O', 'o');
        symbols.put('P', 'p');
        symbols.put('A', 'a');
        symbols.put('S', 's');
        symbols.put('D', 'd');
        symbols.put('F', 'f');
        symbols.put('G', 'g');
        symbols.put('H', 'h');
        symbols.put('J', 'j');
        symbols.put('K', 'k');
        symbols.put('L', 'l');
        symbols.put('Z', 'z');
        symbols.put('X', 'x');
        symbols.put('C', 'c');
        symbols.put('V', 'v');
        symbols.put('B', 'b');
        symbols.put('N', 'n');
        symbols.put('M', 'm');
        return symbols;
    }
}
