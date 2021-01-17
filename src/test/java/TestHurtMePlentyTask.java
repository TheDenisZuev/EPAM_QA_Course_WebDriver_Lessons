import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task.hurtmeplenty.HomePage;

import java.util.concurrent.TimeUnit;

//При выполнении задания необходимо использовать возможности Selenium WebDriver,
// юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:
//
//        1. Открыть https://cloud.google.com/
//        2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
//        3. Запустить поиск, нажав кнопку поиска.
//        4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
//        5. Активировать раздел COMPUTE ENGINE вверху страницы
//        6. Заполнить форму следующими данными:
    //        * Number of instances: 4
    //        * What are these instances for?: оставить пустым
    //        * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
    //        * VM Class: Regular
    //        * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
    //        * Выбрать Add GPUs
    //        * Number of GPUs: 1
    //        * GPU type: NVIDIA Tesla V100
    //        * Local SSD: 2x375 Gb
    //        * Datacenter location: Frankfurt (europe-west3)
    //        * Commited usage: 1 Year
//        7. Нажать Add to Estimate
//        8. Проверить соответствие данных следующих полей: VM Class, Instance type, Region, local SSD, commitment term
//        9. Проверить что сумма аренды в месяц совпадает с суммой получаемой при ручном прохождении теста.

public class TestHurtMePlentyTask {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void HurtMePlentyTask(){
        HomePage homePage = new HomePage(driver)
                .openPage()
                .createSearchRequest("Google Cloud Platform Pricing Calculator")
                .runSearch()
                .chooseResult()
                .activateEngine()
                .fillEngineFrom("4")
                .addEstimate();

            WebElement vmClassSummary = driver
                    .findElement(By.xpath("//md-list-item/div[contains(text(),'VM class')]"));
            Assert.assertTrue(vmClassSummary.getText().contains("regular"));

            WebElement instanceTypeSummary = driver
                    .findElement(By.xpath("//md-list-item/div[contains(text(),'Instance type')]"));
            Assert.assertTrue(instanceTypeSummary.getText().contains("n1-standard-8"));

            WebElement regionSummary = driver
                    .findElement(By.xpath("//md-list-item/div[contains(text(),'Region')]"));
            Assert.assertTrue(regionSummary.getText().contains("Frankfurt"));

            WebElement localSSDSummary = driver
                    .findElement(By.xpath("//md-list-item/div[contains(text(),'local SSD')]"));
            Assert.assertTrue(localSSDSummary.getText().contains("2x375"));

            WebElement commitmentTermSummary = driver
                    .findElement(By.xpath("//md-list-item/div[contains(text(),'Commitment term')]"));
            Assert.assertTrue(commitmentTermSummary.getText().contains("1 Year"));

            WebElement estimatedCost = driver
                    .findElement(By.xpath("//md-content[@id='compute']//b"));
            Assert.assertTrue(estimatedCost.getText().contains("1,082.77"));

    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }
}
