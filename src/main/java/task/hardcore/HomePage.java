package task.hardcore;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class HomePage {
    private WebDriver driver;
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    public static String homePageHandle;
    public static ArrayList<String> tabs;
    public static String estimatedCost;
    public static Double estimateTotalPrice;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchRequest;

    @FindBy(xpath = "//a//b[text() = 'Google Cloud Platform Pricing Calculator']")
    private WebElement neededSearchResult;

    @FindBy(xpath = "//md-tab-item//div[text()='Compute Engine']")
    private WebElement computeEngineActivator;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement inputNumberInstances;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    private WebElement selectOSMenu;

    @FindBy(xpath = "//md-option[@value='free']")
    private WebElement chooseFreeOS;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement selectVMMenu;

    @FindBy(xpath = "//md-option[@id='select_option_78']")
    private WebElement chooseRegularVM;

    @FindBy(xpath = "//md-select[@placeholder='Series']")
    private WebElement selectMachineSeries;

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement chooseSeries;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement selectMachineType;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement chooseMachineType;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement checkboxAddGPU;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement selectNumberGPU;

    @FindBy(xpath = "//md-option[@id='select_option_399']")
    private WebElement chooseNumberGPU;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement selectTypeGPU;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    private WebElement chooseTypeGPU;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
    private WebElement selectLocalSSD;

    @FindBy(xpath = "//md-option[@id='select_option_381']")
    private WebElement chooseLocalSSD;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement selectDatacenter;

    @FindBy(xpath = "//md-option[@id='select_option_205']")
    private WebElement chooseDatacenter;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement selectCommitedUsage;

    @FindBy(xpath = "//md-option[@id='select_option_97']")
    private WebElement chooseCommitedUsage;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[@aria-label='Add to Estimate']")
    private WebElement computeEngineFormButtonAddEstimate;

    @FindBy(xpath = "//button[@id='email_quote']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailEstimateInput;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//h2[@class='md-title']//b[@class='ng-binding']")
    WebElement totalPriceEstimate;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage(){
        driver.get(HOMEPAGE_URL);
        homePageHandle = driver.getWindowHandle();
        return this;
    }

    public HomePage createSearchRequest(String request){
        searchInput.click();
        searchRequest.sendKeys(request);
        return this;
    }

    public HomePage runSearch(){
        searchRequest.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage chooseResult(){
        neededSearchResult.click();
        return this;
    }

    public HomePage activateEngine(){
        driver.switchTo().frame(0);
        driver.switchTo().frame(0);
        computeEngineActivator.click();
        return this;
    }

    public HomePage fillEngineFrom(String numberInstance){
        WebDriverWait wait = (new WebDriverWait(driver, 5));
        inputNumberInstances.sendKeys(numberInstance);
        selectOSMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseFreeOS)).click();
        selectVMMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseRegularVM)).click();
        selectMachineSeries.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseSeries)).click();
        selectMachineType.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseMachineType)).click();
        checkboxAddGPU.click();
        selectNumberGPU.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseNumberGPU)).click();
        selectTypeGPU.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseTypeGPU)).click();
        selectLocalSSD.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseLocalSSD)).click();
        selectDatacenter.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseDatacenter)).click();
        selectCommitedUsage.click();
        wait.until(ExpectedConditions.elementToBeClickable(chooseCommitedUsage)).click();
        return this;
    }

    public HomePage addEstimate(){
        computeEngineFormButtonAddEstimate.click();
        estimatedCost = driver.findElement(By.xpath("//md-content[@id='compute']//b")).getText();
        return this;
    }

    public HomePage getPriceEstimate(){
        String priceEstimate = totalPriceEstimate
                .getText()
                .replace("1 month", "")
                .replaceAll("[^0-9.]", "");
        estimateTotalPrice = Double.parseDouble(priceEstimate);
        return this;
    }

    public HomePage createNewTab(){
        ((JavascriptExecutor) driver).executeScript("window.open()");
        return this;
    }

    public HomePage switchNewTab(){
        tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return this;
    }

    public HomePage sendEmail(){
        driver.switchTo().frame(0);
        driver.switchTo().frame(0);
        emailEstimateButton.click();
        emailEstimateInput.sendKeys(TempEmailPage.emailAddress);
        sendEmailButton.click();
        return this;
    }
}
