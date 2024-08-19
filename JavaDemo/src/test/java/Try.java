import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Try {
    private WebDriver driver;
    private final String correctUsername="Admin";
    private final String correctPassword="admin123";

    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
//        System.setProperty("webdriver.chrome.driver","Drivers/chromedriver");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyWithCorrectUserNameAndPassword() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.findElement(usernameLocator).sendKeys(correctUsername);
        driver.findElement(passwordLocator).sendKeys(correctPassword);
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//header/div[1]/div[1]/span[1]/h6[1]")).getText(), "Dashboard");
    }

    @Test
    public void verifyWithCorrectUserNameButWrongPassword() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.findElement(usernameLocator).sendKeys(correctUsername);
        driver.findElement(passwordLocator).sendKeys("admin1234");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[1]")).getText(), "Invalid credentials");
    }

    @Test
    public void verifyWithBlankUserNameAndPassword() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/span[1]")).getText(), "Required");
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/span[1]")).getText(), "Required");
    }
}
