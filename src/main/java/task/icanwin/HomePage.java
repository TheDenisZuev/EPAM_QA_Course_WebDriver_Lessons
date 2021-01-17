package task.icanwin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private WebDriver driver;

    @FindBy(xpath = "//textarea[@id='postform-text']")
    private WebElement textareaCode;

    @FindBy(xpath = "//span[@id='select2-postform-expiration-container']")
    private WebElement menuTimeExpiration;

    @FindBy(xpath = "//li[text() ='10 Minutes']")
    private WebElement selectTimeExpiration;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement inputNamePaste;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitNewPasteButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage(){
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10);
        return this;
    }

    public HomePage writeCode(String code){
        textareaCode.sendKeys(code);
        return this;
    }

    public HomePage setTimeExpirationTenMin() {
        menuTimeExpiration.click();
        selectTimeExpiration.click();
        return this;
    }

    public HomePage writePasteName(String name){
        inputNamePaste.sendKeys(name);
        return this;
    }

    public HomePage createPaste(){
        submitNewPasteButton.click();
        return this;
    }
}
