import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task.icanwin.HomePage;


//При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка и концепцию Page Object. Автоматизировать следующий сценарий:
//
//        Открыть https://pastebin.com или аналогичный сервис в любом браузере Создать New Paste со следующими деталями:
//        Код: "Hello from WebDriver"
//        Paste Expiration: "10 Minutes"
//        Paste Name / Title: "helloweb"

public class TestICanWinTask {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testICanWin(){
        HomePage homePage = new HomePage(driver)
                .openPage()
                .writeCode("Hello from WebDriver")
                .setTimeExpirationTenMin()
                .writePasteName("helloweb")
                .createPaste();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }
}
