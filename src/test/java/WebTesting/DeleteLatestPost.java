package WebTesting;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.*;
//import factory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DeleteLatestPost extends TestObject {
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"IvetaSF","12345678SF", "5628"},
        };
    }
    @Test(dataProvider = "getUser")
    public void deleteLatestPostTest (String username, String password, String userId){
        WebDriver webDriver = super.getWebDriver();

        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.completeSigIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page" + userId + "user");

        profilePage.clickButtonHoverDelete();
        //profilePage.clickButtonDelete();


       // profilePage.checkDeleteMessage();
        Assert.assertTrue(loginPage.isLoginMessageDisplayed(), "Delete");

    }
}