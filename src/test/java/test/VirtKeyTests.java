package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.VirtKeyHomePage;

public class VirtKeyTests extends test.CommonConditions {
    @Test (description = "Open 'Virtual Keyboard' page test")
    public void openVirtualKeyboardPage() {

        new VirtKeyHomePage(driver).openPage();

        Assert.assertTrue (driver.getCurrentUrl()
                .equals("https://virtual-keyboard-app.vercel.app/"), "URLs aren't equals!");

    }
}
