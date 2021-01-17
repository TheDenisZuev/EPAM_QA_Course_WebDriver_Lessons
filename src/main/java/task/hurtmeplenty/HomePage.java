package task.hurtmeplenty;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";

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

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage(){
        driver.get(HOMEPAGE_URL);
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
        inputNumberInstances.sendKeys(numberInstance);
        selectOSMenu.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseFreeOS)).click();
        selectVMMenu.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseRegularVM)).click();
        selectMachineSeries.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseSeries)).click();
        selectMachineType.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseMachineType)).click();
        checkboxAddGPU.click();
        selectNumberGPU.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseNumberGPU)).click();
        selectTypeGPU.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseTypeGPU)).click();
        selectLocalSSD.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseLocalSSD)).click();
        selectDatacenter.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseDatacenter)).click();
        selectCommitedUsage.click();
        new WebDriverWait(driver, 15).until(
                ExpectedConditions.elementToBeClickable(chooseCommitedUsage)).click();
        return this;
    }

    public HomePage addEstimate(){
        computeEngineFormButtonAddEstimate.click();
        return this;
    }
}
