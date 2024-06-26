package WebTesting;
import factory.*;
//import object.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.N;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class NewPostTest extends TestObject {
    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        File postPic = new File("src//test//resources//upload//greece.jpg");
        String caption = "Testing upload file";
        return new Object[][]{

                {"IvetaSF", "12345678SF", "5628", postPic, caption}
        };
    }

    @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPic, String caption) {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        NewPost newPost = new NewPost(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.completeSigIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId),"Current page is not profile page"+ userId + "user");

        header.clickNewPost();
        Assert.assertTrue(newPost.isNewPostLoaded(),"The new posr form is not loaded");

        newPost.uploadPicture(postPic);
        String actualImageText = newPost.uploadedImageText();
        Assert.assertTrue(newPost.isImageUploaded("greece.jpg"),"Image is not uploaded");
        Assert.assertEquals(actualImageText, "greece.jpg", "Incorect image is uploaded");

        newPost.typePostCaption(caption);
        newPost.clickCreatePost();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page!");


        loginPage.checkLoginMessage();
        Assert.assertTrue(loginPage.isLoginMessageDisplayed(), "New Post message is not displayed");

    }
}