package WebTesting;

//import factory.*;
import object.*;

import io.github.bonigarcia.wdm.WebDriverManager;
//import object.Header;
//import object.HomePage;
//import object.LoginPage;
//import object.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest extends TestObject {

    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                //To pass the first data object the userId needs to be changed to 5508
                {"IvetaSF","12345678SF", "5628"},

        };
    }

    @Test(dataProvider = "getUser")
    public void loginTest(String username, String password, String userId){
        //String homeURL = "http://training.skillo-bg.com:4200/posts/all";
        //webDriver.get(homeURL);
        //String currentURL = webDriver.getCurrentUrl();
        //Assert.assertEquals(currentURL,homeURL);
        WebDriver webDriver = super.getWebDriver();

        HomePage homePage = new HomePage(webDriver);
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");

        //WebElement loginLink = webDriver.findElement(By.id("nav-link-login"));
        //loginLink.click();
        header.clickLogin();

        //String loginURL = "http://training.skillo-bg.com:4200/users/login";

//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        //wait.until(ExpectedConditions.urlToBe(loginURL));
        // gornite 2 zakomentirani reda veche sa kombinirani v dolniq:
        Assert.assertTrue(loginPage.isUrlLoaded(), "Login page is not loaded");

//        WebElement usernameTextField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id("defaultLoginFormUsername"))));
//        usernameTextField.sendKeys(username);
//
//        WebElement passwordTestField = wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//form/input[@id='defaultLoginFormPassword']"))));
//        passwordTestField.sendKeys(password);

        //gornite 4 zakomentirani reda veche sa kombinirane v dolnite 2:
        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);

//        WebElement rememberMeCheckbox = webDriver.findElement(By.xpath("//*[@class='remember-me']/input[@type='checkbox']"));
//        wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
//        rememberMeCheckbox.click();
        loginPage.checkRememberMe();
//        Assert.assertTrue(rememberMeCheckbox.isSelected());
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is checked");

//        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.id("sign-in-button"))));
//        signInButton.click();

        loginPage.clickSignInButton();
        header.clickProfile();

//        String userProfileURL = "http://training.skillo-bg.com:4200/users/" + userId;
//        wait.until(ExpectedConditions.urlToBe(userProfileURL));

//        Assert.assertEquals(webDriver.getCurrentUrl(),"http://training.skillo-bg.com:4200/users/" + userId);
        Assert.assertTrue(profilePage.isUrlLoaded(), "Profile page is not loaded");

    }
}