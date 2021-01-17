import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import task.bringiton.HomePage;

import java.util.concurrent.TimeUnit;

//При выполнении задания необходимо использовать возможности Selenium WebDriver, юнит-тест фреймворка
// и концепцию Page Object. Автоматизировать следующий сценарий:
//
//        Открыть https://pastebin.com  или аналогичный сервис в любом браузере
//        Создать New Paste со следующими деталями:
//        * Код:
//        git config --global user.name  "New Sheriff in Town"
//        git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
//        git push origin master --force
//        * Syntax Highlighting: "Bash"
//        * Paste Expiration: "10 Minutes"
//        * Paste Name / Title: "how to gain dominance among developers"
//        3. Сохранить paste и проверить следующее:
//        * Заголовок страницы браузера соответствует Paste Name / Title
//        * Синтаксис подвечен для bash
//        * Проверить что код соответствует введенному в пункте 2

public class TestBringItOnTask {
    WebDriver driver;
    String CODE_SAMPLE = "git config --global user.name  \\\"New Sheriff in Town\\\"\\n\" +\n" +
            "            \"git reset $(git commit-tree HEAD^{tree} -m \\\"Legacy code\\\")\\n\" +\n" +
            "            \"git push origin master --force\";\n" +
            "    String namePasteSample = \"how to gain dominance among developers";
    String NAME_PASTE_SAMPLE = "how to gain dominance among developers";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testBringItOn(){
        HomePage homePage = new HomePage(driver)
                .openPage()
                .writeCode(CODE_SAMPLE)
                .setSyntaxBush()
                .setTimeExpirationTenMin()
                .writePasteName(NAME_PASTE_SAMPLE)
                .createPaste();

        WebElement pasteName = driver.findElement(By.xpath("//div[@class='info-top']//h1"));
        WebElement verifyBashCode = driver.findElement(By.xpath("//ol[@class='bash']"));
        WebElement verifySyntaxBash = driver.findElement(By.xpath("//ol[@class='bash']"));
        Assert.assertEquals(NAME_PASTE_SAMPLE, pasteName.getText(),
                "PasteName is not correct, current PasteName " + pasteName.getText());
        Assert.assertEquals(CODE_SAMPLE, verifyBashCode.getText(),
                "Sample code does't have bash syntax");
        Assert.assertTrue(verifySyntaxBash.isDisplayed(), "Bash syntax is not highlighted");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
        driver = null;
    }
}
