import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task.hardcore.HomePage;
import task.hardcore.TempEmailPage;

import java.util.concurrent.TimeUnit;
//    При выполнении задания необходимо использовать возможности Selenium WebDriver,
//    юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:
//
//  1. Открыть https://cloud.google.com/
//  2. Нажав кнопку поиска по порталу вверху страницы, ввести в поле поиска"Google Cloud Platform Pricing Calculator"
//  3. Запустить поиск, нажав кнопку поиска.
//  4. В результатах поиска кликнуть "Google Cloud Platform Pricing Calculator" и перейти на страницу калькулятора.
//  5. Активировать раздел COMPUTE ENGINE вверху страницы
//  6. Заполнить форму следующими данными:
//    * Number of instances: 4
//    * What are these instances for?: оставить пустым
//    * Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS
//    * VM Class: Regular
//    * Instance type: n1-standard-8    (vCPUs: 8, RAM: 30 GB)
//    * Выбрать Add GPUs
//    * Number of GPUs: 1
//    * GPU type: NVIDIA Tesla V100
//    * Local SSD: 2x375 Gb
//    * Datacenter location: Frankfurt (europe-west3)
//    * Commited usage: 1 Year
//  7. Нажать Add to Estimate
//  8. Выбрать пункт EMAIL ESTIMATE
//  9. В новой вкладке открыть https://10minutemail.com или аналогичный сервис для генерации временных email'ов
//            10. Скопировать почтовый адрес сгенерированный в 10minutemail
//  11. Вернуться в калькулятор, в поле Email ввести адрес из предыдущего пункта
//  12. Нажать SEND EMAIL
//  13. Дождаться письма с рассчетом стоимости и проверить что Total Estimated Monthly Cost в письме совпадает с тем,
//  что отображается в калькуляторе

public class TestHardcoreTask {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void HurtMePlentyTask() throws InterruptedException {
        HomePage homePage = new HomePage(driver)
                .openPage()
                .createSearchRequest("Google Cloud Platform Pricing Calculator")
                .runSearch()
                .chooseResult()
                .activateEngine()
                .fillEngineFrom("4")
                .addEstimate()
                .getPriceEstimate()
                .createNewTab()
                .switchNewTab();
        TempEmailPage tempEmailPage = new TempEmailPage(driver)
                .openPage()
                .copyEmail()
                .switchOldTab();
        Thread.sleep(10);
        homePage
                .sendEmail()
                .switchNewTab();
        tempEmailPage
                .openMessage()
                .getEmailEstimatePrice()
                .switchOldTab();

        Assert.assertEquals(HomePage.estimateTotalPrice, TempEmailPage.priceEstimateEmail);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }


}
