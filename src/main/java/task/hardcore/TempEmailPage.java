package task.hardcore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TempEmailPage {
    private WebDriver driver;
    private static final String TEMP_EMAIL_URL = "https://10minutemail.com/";
    public static String emailAddress;
    public static String tempEmailHandle;
    public static Double priceEstimateEmail;



    @FindBy(xpath = "//input[@id='mail_address']")
    WebElement inputEmail;

    @FindBy(xpath = "//div[@class='small_sender']//span")
    WebElement incomingMail;

    @FindBy(xpath = "//h3[contains(.,'USD')]")
    WebElement incomingEmailPrice;

    public TempEmailPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public TempEmailPage openPage(){
        driver.get(TEMP_EMAIL_URL);
        tempEmailHandle = driver.getWindowHandle();
        return this;
    }

    public TempEmailPage copyEmail(){
        emailAddress = inputEmail.getAttribute("value");
        return this;
    }

    public TempEmailPage switchOldTab(){
        driver.switchTo().window(HomePage.homePageHandle);
        return this;
    }

    public TempEmailPage openMessage(){
        WebDriverWait wait = (new WebDriverWait(driver, 90));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='small_sender']//span"))).click();
        return this;
    }

    public TempEmailPage getEmailEstimatePrice(){
        String emailPrice = incomingEmailPrice.getText().replaceAll("[^0-9.]", "");
        priceEstimateEmail = Double.parseDouble(emailPrice);
        return this;
    }
}
