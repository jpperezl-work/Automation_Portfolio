// LoginTest.java
package tests;

import pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void invalidLoginTest() {
        loginPage.enterUsername("invalid");
        loginPage.enterPassword("wrong");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @AfterClass
    public void teardown() { driver.quit(); }
}
