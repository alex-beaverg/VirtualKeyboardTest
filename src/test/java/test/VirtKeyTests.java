// mvn -Dbrowser=edge -Dsurefire.suiteXmlFiles=src\test\resources\tests.xml clean test
package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.VirtKeyHomePage;

public class VirtKeyTests extends test.CommonConditions {
    @Test (description = "Open 'Virtual Keyboard' page test")
    public void t01_openKeyboardPage() {

        new VirtKeyHomePage(driver)
                .openPage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://virtual-keyboard-app.vercel.app/",
                "URLs aren't equals!");
    }

    @Test (description = "Show 'Virtual Keyboard' on the page test")
    public void t02_showKeyboard() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard();

        Assert.assertNotNull(driver.findElement(By.className("keyboard")));
    }

    @Test (description = "Maximize output textarea")
    public void t03_maximizeOutputTextarea() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea();

        Assert.assertEquals(driver
                .findElement(By.className("use-keyboard-input")).getCssValue("height"),"450px");
    }

    @Test (description = "Write text without Shift in English test")
    public void t04_writeTextWithoutShiftInEnglish() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("hello ;-=`'123\\/.[]");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "hello ;-=`'123\\/.[]");
    }

    @Test (description = "Write text with Shift in English test")
    public void t05_writeTextWithShiftInEnglish() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("HELLO ~!@#$%^&*()_+{}|:\"<>?");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "HELLO ~!@#$%^&*()_+{}|:\"<>?");
    }

    @Test (description = "Write text with and without Shift in English test")
    public void t06_writeTextWithAndWithoutShiftInEnglish() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("HeLLo ~!@#$%her^&*()345_+{}|:\"<[]>?");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "HeLLo ~!@#$%her^&*()345_+{}|:\"<[]>?");
    }

    @Test (description = "Write text without Shift in Russian test")
    public void t07_writeTextWithoutShiftInRussian() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("ё1234567890-=йцуке нгшщзхъ\nфывапролджэ\\ячсмитьбю.");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "ё1234567890-=йцуке нгшщзхъ\nфывапролджэ\\ячсмитьбю.");
    }

    @Test (description = "Write text with Shift in Russian test")
    public void t08_writeTextWithShiftInRussian() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("Ё!\"№;%:?*()_+ЙЦУКЕНГШЩЗХЪ\nФЫВА ПРОЛДЖЭ|ЯЧСМИТБЬЮ,");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "Ё!\"№;%:?*()_+ЙЦУКЕНГШЩЗХЪ\nФЫВА ПРОЛДЖЭ|ЯЧСМИТБЬЮ,");
    }

    @Test (description = "Write text with and without Shift in Russian test")
    public void t09_writeTextWithAndWithoutShiftInRussian() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("Ё123;%:?*90_+ЙцукенГШЩЗХЪ\nФЫва ПРОлджЭ|ЯЧСмитБЬЮ,");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "Ё123;%:?*90_+ЙцукенГШЩЗХЪ\nФЫва ПРОлджЭ|ЯЧСмитБЬЮ,");
    }

    @Test (description = "Write text with and without Shift in Russian and English test")
    public void t10_writeTextWithAndWithoutShiftInRussianAndEnglish() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("`123;%:?*90_+Йцу1rtyГШopХЪ\nФ{Ы}ва [] GHОлджЭ|ЯЧСм///bтБЬЮ,");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "`123;%:?*90_+Йцу1rtyГШopХЪ\nФ{Ы}ва [] GHОлджЭ|ЯЧСм///bтБЬЮ,");
    }

    @Test (description = "Write agile manifesto")
    public void t11_writeAgileManifesto() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("""
                        We are uncovering better ways of developing
                        software by doing it and helping others do it.
                        Through this work we have come to value:

                        Individuals and interactions over processes and tools
                        Working software over comprehensive documentation
                        Customer collaboration over contract negotiation
                        Responding to change over following a plan

                        That is, while there is value in the items on
                        the right, we value the items on the left more.""");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"), """
                        We are uncovering better ways of developing
                        software by doing it and helping others do it.
                        Through this work we have come to value:

                        Individuals and interactions over processes and tools
                        Working software over comprehensive documentation
                        Customer collaboration over contract negotiation
                        Responding to change over following a plan

                        That is, while there is value in the items on
                        the right, we value the items on the left more.""");
    }

    @Test (description = "Write text with left and right arrows")
    public void t12_writeTextWithArrows() {

        VirtKeyHomePage page = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("Hello my dear friend!")
                .clickArrowLeft(12)
                .writeText("happy ")
                .clickArrowRight(12)
                .writeText(" I'm glad to see you!");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "Hello my happy dear friend! I'm glad to see you!");
    }

    @Test (description = "Write text with backspace")
    public void t13_writeTextWithBackspace() {

        VirtKeyHomePage page = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("Hello my dear friend!")
                .clickArrowLeft(12)
                .writeText("happy ")
                .clickArrowRight(12)
                .writeText(" I'm glad to see you!")
                .clickArrowLeft(22)
                .clickBackspace(6)
                .writeText("girl");

        Assert.assertEquals(driver.findElement(By.tagName("textarea")).getAttribute("value"),
                "Hello my happy dear girl! I'm glad to see you!");
    }
}
