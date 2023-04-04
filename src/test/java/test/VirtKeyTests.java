package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.VirtKeyHomePage;

public class VirtKeyTests extends test.CommonConditions {
    @Test (description = "Open 'Virtual Keyboard' page test")
    public void t1_openVirtualKeyboardPage() {

        new VirtKeyHomePage(driver)
                .openPage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://virtual-keyboard-app.vercel.app/",
                "URLs aren't equals!");
    }

    @Test (description = "Show 'Virtual Keyboard' on the page")
    public void t2_showVirtualKeyboardOnThePage() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard();

        Assert.assertNotNull(driver.findElement(By.className("keyboard")));
    }

    @Test (description = "Write text with the virtual keyboard")
    public void t3_writeTextWithTheVirtualKeyboardOnlyWithoutShift() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .writeText("hello ;-=`'123\\/.[]");

        Assert.assertEquals(text, "hello ;-=`'123\\/.[]");
    }

    @Test (description = "Write text with the virtual keyboard")
    public void t4_writeTextWithTheVirtualKeyboardOnlyWithShift() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .writeText("HELLO ~!@#$%^&*()_+{}|:\"<>?");

        Assert.assertEquals(text, "HELLO ~!@#$%^&*()_+{}|:\"<>?");
    }

    @Test (description = "Write text with the virtual keyboard")
    public void t5_writeTextWithTheVirtualKeyboardWithAndWithoutShift() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .writeText("HeLLo ~!@#$%her^&*()345_+{}|:\"<[]>?");

        Assert.assertEquals(text, "HeLLo ~!@#$%her^&*()345_+{}|:\"<[]>?");
    }
}
