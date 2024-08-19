// Importing required classes from the Selenium library
import org.openqa.selenium.By; // For locating elements
import org.openqa.selenium.Dimension; // For setting browser dimensions
import org.openqa.selenium.WebDriver; // Main interface to control the browser
import org.openqa.selenium.chrome.ChromeDriver; // Implementation for Chrome
import org.testng.Assert; // For assertion in tests
import org.testng.annotations.*; // For using TestNG annotations

import java.time.Duration; // For specifying timeouts

public class SeleniumDemo {
    // Declare the WebDriver instance that will control the browser
    private WebDriver driver;

    // This method runs before each test method. It sets up the browser environment.
    @BeforeMethod
    public void setUp() {
        // Instantiate the ChromeDriver (opens a new Chrome browser)
        driver = new ChromeDriver();

        // The commented line below is used to set the path for the ChromeDriver executable if it's not in the system PATH
        // System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver");

        // Set the implicit wait timeout to 15 seconds. This makes WebDriver wait up to 15 seconds for elements to appear.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    // This method runs after each test method. It closes the browser.
    @AfterMethod
    public void tearDown() {
        // Quit the driver, closing all browser windows and ending the WebDriver session
        driver.quit();
    }

    // Test method to verify login with correct username and password
    @Test
    public void verifyWithCorrectUserNameAndPassword() {
        // Navigate to the login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Maximize the browser window
        driver.manage().window().maximize();

        // Find the username input field and enter the text "Admin"
        driver.findElement(By.name("username")).sendKeys("Admin");

        // Find the password input field and enter the text "admin123"
        driver.findElement(By.name("password")).sendKeys("admin123");

        // Find the login button by its text and click it
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        // Assert that the dashboard page is displayed by checking the text of a specific element
        Assert.assertEquals(driver.findElement(By.xpath("//header/div[1]/div[1]/span[1]/h6[1]")).getText(), "Dashboard");
    }

    // Test method to verify login with correct username but wrong password
    @Test
    public void verifyWithCorrectUserNameButWrongPassword() {
        // Navigate to the login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Maximize the browser window
        driver.manage().window().maximize();

        // Find the username input field and enter the text "Admin"
        driver.findElement(By.name("username")).sendKeys("Admin");

        // Find the password input field and enter the text "admin1234" (wrong password)
        driver.findElement(By.name("password")).sendKeys("admin1234");

        // Find the login button by its text and click it
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        // Assert that the login fails by checking the error message
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/p[1]")).getText(), "Invalid credentials");
    }

    // Test method to verify login with blank username and password
    @Test
    public void verifyWithBlankUserNameAndPassword() {
        // Navigate to the login page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Maximize the browser window
        driver.manage().window().maximize();

        // Find the login button by its text and click it without entering username and password
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();

        // Assert that the required field error messages are displayed
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[1]/div[1]/span[1]")).getText(), "Required");
        Assert.assertEquals(driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/form[1]/div[2]/div[1]/span[1]")).getText(), "Required");
    }
}
