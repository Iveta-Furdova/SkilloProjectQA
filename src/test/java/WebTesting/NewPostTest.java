package WebTesting;
import factory.*;
import io.github.bonigarcia.wdm.WebDriverManager;
//import object.Header;
//import object.HomePage;
//import object.LoginPage;
//import object.ProfilePage;
//import object.NewPost;
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
        File postPic = new File("src//test//resources//upload//testimage.jpg");
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

        loginPage.completeSingIn(username, password);

        // Add wait for the profile page to load


        // After the profile page loads, proceed with the test
        //profilePage = new ProfilePage(webDriver);
        //header.clickNewPost();
       // Assert.assertTrue(profilePage.isUrlLoaded(), "The new post page is not loaded");

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId),"Current page is not profile page"+ userId + "user");

        header.clickNewPost();
        Assert.assertTrue(newPost.isNewPostLoaded(),"The new posr form is not loaded");

        newPost.uploadPicture(postPic);
        String actualImageText = newPost.uploadedImageText();
        Assert.assertTrue(newPost.isImageUploaded("testimage.jpg"),"Image is not uploaded");
        Assert.assertEquals(actualImageText, "testimage.jpg", "Incorect image is uploaded");

        newPost.typePostCaption(caption);
        newPost.clickCreatePost();

        Assert.assertTrue(profilePage.isUrlLoaded(userId),"Current page is not progile page for" + userId + "user");

    }
}