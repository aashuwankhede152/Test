package tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.ApplicationHeader;
import pom.LoginPage;

public class VerifyHeaderOfApplication {

    private WebDriver driver;
    private ApplicationHeader applicationHeader;
    private LoginPage loginPage;
	
	@BeforeClass
	public void launchBrowser()
	{
		System.out.println("Launch Browser");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashwini\\Automation Jar\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
	
	@BeforeMethod
    public void loginToApplication()
	{
		System.out.println("Login to application");
		driver.get("http://localhost/login.do");
		loginPage = new LoginPage(driver);
		loginPage.sendUserName();
		loginPage.sendPassword();
		loginPage.clickOnLogin();
		applicationHeader = new ApplicationHeader(driver);
	}
	
	@Test
	public void toVerifyTaskButton() 
	{
		System.out.println("ToVerifyTaskButton");
		applicationHeader.openTaskPage();
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		
		//expected is equals to actual the test PASS
		//expected is not equals to actual the test FAIL
		Assert.assertEquals(url, "http://localhost/tasks/otasklist.do", "url of task is not found");
		Assert.assertEquals(title, "actiTIME -  Open Tasks", "title of is not found");
////	expected is equals to actual the test FAIL
////	expected is not equals to actual the test PASS
////	Assert.assertNotEquals(url, "http://localhost/tasks/otasklist.do");
//		
//	    boolean result = url.equals("http://localhost/tasks/otasklist.do");
//	    
//	    //RESULT == true ==> Test PASS
//	    //RESULT == false ==> Test FAIl
//	    Assert.assertTrue(result);
//	    
//	    //RESULT == true ==> Test FAIL
//	    //RESULT == false ==> Test PASS
//	    Assert.assertFalse(result);
//	    Assert.fail();
	}
	@Test
	public void toVerifyUserButton() {
		System.out.println("To verify user button");
		applicationHeader.openUserPage();
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		Assert.assertEquals(url, "http://localhost/administration/userlist.do", "url of user is not found");
		Assert.assertEquals(title, "actiTIME -  User List ", "title of user is not found");
		
	}
	
	    @AfterMethod
	    public void logoutFromApplication() {
		System.out.println("logout");
		applicationHeader.logoutFromApplication();
      	}
	
     	@AfterClass
	    public void closeBrowser() {
		System.out.println("close browser");
		driver.quit();
	}
			
	}
