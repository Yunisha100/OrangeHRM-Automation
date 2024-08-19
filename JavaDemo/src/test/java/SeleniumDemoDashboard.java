import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SeleniumDemoDashboard {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyPartialTextSearchWithOneResult() {
        driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("Dir");
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/ul[1]/li[1]/a[1]/span[1]")).getText(), "Directory");
    }
    @Test
    public void verifyPartialTextSearchWithMoreThanOneResult() {
        driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/aside[1]/nav[1]/div[2]/div[1]/div[1]/input[1]")).sendKeys("D");
        List<WebElement> list = driver.findElements(By.xpath("//ul/li/a/span"));
        for(WebElement e : list){
            Assert.assertTrue(e.getText().toLowerCase().contains("d"));
        }
    }
}
