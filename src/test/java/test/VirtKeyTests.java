package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.VirtKeyHomePage;

public class VirtKeyTests extends test.CommonConditions {
    @Test (description = "Open 'Virtual Keyboard' page test")
    public void openVirtualKeyboardPage() {

        new VirtKeyHomePage(driver)
                .openPage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://virtual-keyboard-app.vercel.app/",
                "URLs aren't equals!");
    }

    @Test (description = "Show 'Virtual Keyboard' on the page")
    public void showVirtualKeyboardOnThePage() {

        new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard();

        Assert.assertNotNull(driver.findElement(By.className("keyboard")));
    }

    @Test (description = "Write text with the virtual keyboard")
    public void writeTextWithTheVirtualKeyboard() {

        String text = new VirtKeyHomePage(driver)
                .openPage()
                .showKeyboard()
                .writeText("hello ;-=`123\\/.[]");

        Assert.assertEquals(text, "hello ;-=`123\\/.[]");
    }
}
