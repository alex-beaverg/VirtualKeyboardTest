package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import waits.ExplicitCondition;

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
        logger.info("Opened 'Virtual Keyboard' page with URL: [" + HOMEPAGE_URL + "]");
        return this;
    }
}
