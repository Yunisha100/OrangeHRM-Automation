import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class usingFunction {
    private WebDriver driver;
    private final String correctUsername="Admin";
    private final String correctPassword="admin123";

    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");
    By loginLocator = By.cssSelector(".oxd-button");

    public void login(String username, String password){
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginLocator).click();
    }
//    public void login(){
//        driver.findElement(usernameLocator).sendKeys(username);
//        driver.findElement(passwordLocator).sendKeys(password);
//        driver.findElement(loginLocator).click();
//    }


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
//        System.setProperty("webdriver.chrome.driver","Drivers/chromedriver");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyWithCorrectUserNameAndPassword() {

        login(correctUsername, correctPassword);
        Assert.assertEquals(driver.findElement(By.xpath("//header/div[1]/div[1]/span[1]/h6[1]")).getText(), "Dashboard");
    }

    @Test
    public void verifyWithCorrectUserNameButWrongPassword() {

       login(correctUsername, "abcd");
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[1]")).getText(), "Invalid credentials");
    }

    @Test
    public void verifyWithBlankUserNameAndPassword() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.findElement(loginLocator).click();
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/span[1]")).getText(), "Required");
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/span[1]")).getText(), "Required");
    }
}
