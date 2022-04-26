package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {

    String baseUrl = "https://parabank.parasoft.com/parabank/index.htm";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() {
        //valid user name
        sendTextToElement(By.xpath("//input[@name='username']"),"SunderPraptichekam");

        // valid password
        sendTextToElement(By.xpath("//body/div[@id='mainPanel']/div[@id='bodyPanel']/div[@id='leftPanel']/div[@id='loginPanel']/form[1]/div[3]/input[1]"),"123456");

        //click on login
        clickOnElement(By.xpath("//input[@value='Log In']"));


    }

    @Test
    public void verifyTheErrorMessage() {
        //Enter Invalid user Name
        sendTextToElement(By.xpath("//input[@name='username']"),"underprapti");

        // Enter Invalid Password
        sendTextToElement(By.xpath("//input[@name='password']"),"123789");

        //click on login
        clickOnElement(By.xpath("//input[@value='Log In']"));

        //verification of error message
        WebElement actualMessageElement = driver.findElement(By.xpath("//p[text()='The username and password could not be verified.']"));
        String actualMessage = actualMessageElement.getText();
        String expectedMessage = "The username and password could not be verified.";

        Assert.assertEquals("Message printed correctly", actualMessage, expectedMessage);

    }

    @Test
    public void userShouldLogoutSuccessfully() {
        //Enter valid user name
        sendTextToElement(By.xpath("//input[@name='username']"),"SunderPraptichekam");

        // Enter valid password
        sendTextToElement(By.xpath("//input[@name='password']"),"123456");

        //click on login button
        clickOnElement(By.xpath("//input[@value='Log In']"));

        //click on log out button
        //click on logout
        clickOnElement(By.xpath("//a[text()='Log Out']"));

        WebElement actualMessageElement = driver.findElement(By.xpath("//h2[text()='Customer Login']"));
        String acutalMessage = actualMessageElement.getText();
        String expectedMessage = "Customer Login";

        Assert.assertEquals("Customer Login", acutalMessage,expectedMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
