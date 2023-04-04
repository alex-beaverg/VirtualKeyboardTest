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

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("hello ;-=`'123\\/.[]");

        Assert.assertEquals(text, "hello ;-=`'123\\/.[]");
    }

    @Test (description = "Write text with Shift in English test")
    public void t05_writeTextWithShiftInEnglish() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("HELLO ~!@#$%^&*()_+{}|:\"<>?");

        Assert.assertEquals(text, "HELLO ~!@#$%^&*()_+{}|:\"<>?");
    }

    @Test (description = "Write text with and without Shift in English test")
    public void t06_writeTextWithAndWithoutShiftInEnglish() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("HeLLo ~!@#$%her^&*()345_+{}|:\"<[]>?");

        Assert.assertEquals(text, "HeLLo ~!@#$%her^&*()345_+{}|:\"<[]>?");
    }

    @Test (description = "Write text without Shift in Russian test")
    public void t07_writeTextWithoutShiftInRussian() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("ё1234567890-=йцуке нгшщзхъ\nфывапролджэ\\ячсмитьбю.");

        Assert.assertEquals(text, "ё1234567890-=йцуке нгшщзхъ\nфывапролджэ\\ячсмитьбю.");
    }

    @Test (description = "Write text with Shift in Russian test")
    public void t08_writeTextWithShiftInRussian() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("Ё!\"№;%:?*()_+ЙЦУКЕНГШЩЗХЪ\nФЫВА ПРОЛДЖЭ|ЯЧСМИТБЬЮ,");

        Assert.assertEquals(text, "Ё!\"№;%:?*()_+ЙЦУКЕНГШЩЗХЪ\nФЫВА ПРОЛДЖЭ|ЯЧСМИТБЬЮ,");
    }

    @Test (description = "Write text with and without Shift in Russian test")
    public void t09_writeTextWithAndWithoutShiftInRussian() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("Ё123;%:?*90_+ЙцукенГШЩЗХЪ\nФЫва ПРОлджЭ|ЯЧСмитБЬЮ,");

        Assert.assertEquals(text, "Ё123;%:?*90_+ЙцукенГШЩЗХЪ\nФЫва ПРОлджЭ|ЯЧСмитБЬЮ,");
    }

    @Test (description = "Write text with and without Shift in Russian and English test")
    public void t10_writeTextWithAndWithoutShiftInRussianAndEnglish() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("`123;%:?*90_+ЙцуrtyГШopХЪ\nФ{Ы}ва [] GHОлджЭ|ЯЧСм///bтБЬЮ,");

        Assert.assertEquals(text, "`123;%:?*90_+ЙцуrtyГШopХЪ\nФ{Ы}ва [] GHОлджЭ|ЯЧСм///bтБЬЮ,");
    }

    @Test (description = "Write agile manifesto")
    public void t11_writeAgileManifesto() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .maximizeTextArea()
                .writeText("We are uncovering better ways of developing\n" +
                        "software by doing it and helping others do it.\n" +
                        "Through this work we have come to value:\n" +
                        "\n" +
                        "Individuals and interactions over processes and tools\n" +
                        "Working software over comprehensive documentation\n" +
                        "Customer collaboration over contract negotiation\n" +
                        "Responding to change over following a plan\n" +
                        "\n" +
                        "That is, while there is value in the items on\n" +
                        "the right, we value the items on the left more.");

        Assert.assertEquals(text, "We are uncovering better ways of developing\n" +
                "software by doing it and helping others do it.\n" +
                "Through this work we have come to value:\n" +
                "\n" +
                "Individuals and interactions over processes and tools\n" +
                "Working software over comprehensive documentation\n" +
                "Customer collaboration over contract negotiation\n" +
                "Responding to change over following a plan\n" +
                "\n" +
                "That is, while there is value in the items on\n" +
                "the right, we value the items on the left more.");
    }
}
