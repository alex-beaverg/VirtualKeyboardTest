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
        return new HashMap<>() {{
            put('~', '`'); put('!', '1'); put('@', '2'); put('#', '3'); put('$', '4'); put('%', '5'); put('^', '6');
            put('&', '7'); put('*', '8'); put('(', '9'); put(')', '0'); put('_', '-'); put('+', '='); put('{', '[');
            put('}', ']'); put(':', ';'); put('<', ','); put('>', '.'); put('?', '/'); put('Q', 'q'); put('W', 'w');
            put('E', 'e'); put('R', 'r'); put('T', 't'); put('Y', 'y'); put('U', 'u'); put('I', 'i'); put('O', 'o');
            put('P', 'p'); put('A', 'a'); put('S', 's'); put('D', 'd'); put('F', 'f'); put('G', 'g'); put('H', 'h');
            put('J', 'j'); put('K', 'k'); put('L', 'l'); put('Z', 'z'); put('X', 'x'); put('C', 'c'); put('V', 'v');
            put('B', 'b'); put('N', 'n'); put('M', 'm'); put('"', '\''); put('|', '\\');
        }};
    }
}
