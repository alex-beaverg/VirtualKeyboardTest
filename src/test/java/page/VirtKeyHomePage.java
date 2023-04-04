package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
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

    public String writeText(String str) {
        StringBuilder text = new StringBuilder();
        String keyLetter;
        boolean isShift = false;
        boolean isRussian = false;
        HashMap<Character, Character> EnShiftMap = createEnglishShiftMap();
        HashMap<Character, Character> RuShiftMap = createRussianShiftMap();
        HashMap<Character, Character> EnRuMap = createEnRuMap();

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
            text.append(letter);
        }

        logger.info("Was wrote text '" + str + "'");
        return text.toString();
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

    private HashMap<Character, Character> createEnglishShiftMap() {
        logger.info("Was created english Shift Map");
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

    private HashMap<Character, Character> createRussianShiftMap() {
        logger.info("Was created russian Shift Map");
        return new HashMap<>() {{
            put('Ё', 'ё'); put('Й', 'й'); put('Ц', 'ц'); put('У', 'у'); put('К', 'к'); put('Е', 'е'); put('Н', 'н');
            put('Г', 'г'); put('Ш', 'ш'); put('Щ', 'щ'); put('З', 'з'); put('Х', 'х'); put('Ъ', 'ъ'); put('Ф', 'ф');
            put('Ы', 'ы'); put('В', 'в'); put('А', 'а'); put('П', 'п'); put('Р', 'р'); put('О', 'о'); put('Л', 'л');
            put('Д', 'д'); put('Ж', 'ж'); put('Э', 'э'); put('Я', 'я'); put('Ч', 'ч'); put('С', 'с'); put('М', 'м');
            put('И', 'и'); put('Т', 'т'); put('Ь', 'ь'); put('Б', 'б'); put('Ю', 'ю');
        }};
    }

    private HashMap<Character, Character> createEnRuMap() {
        logger.info("Was created EN/RU Map");
        return new HashMap<>() {{
            put('ё', '`'); put('й', 'q'); put('ц', 'w'); put('у', 'e'); put('к', 'r'); put('е', 't'); put('н', 'y');
            put('г', 'u'); put('ш', 'i'); put('щ', 'o'); put('з', 'p'); put('х', '['); put('ъ', ']'); put('ф', 'a');
            put('ы', 's'); put('в', 'd'); put('а', 'f'); put('п', 'g'); put('р', 'h'); put('о', 'j'); put('л', 'k');
            put('д', 'l'); put('ж', ';'); put('э', '\''); put('я', 'z'); put('ч', 'x'); put('с', 'c'); put('м', 'v');
            put('и', 'b'); put('т', 'n'); put('ь', 'm'); put('б', ','); put('ю', '.');
        }};
    }
}
