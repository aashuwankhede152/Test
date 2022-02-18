package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import base.Browser;
import pom.ApplicationHeader;
import pom.LoginPage;
import utils.Utility;

public class Final_VerifyHeaderOfApplication extends Browser {
	private WebDriver driver;
    private ApplicationHeader applicationHeader;
    private LoginPage loginPage;
    private int testID;
    static ExtentReports tests;
    static ExtentHtmlReporter reporter;
    
	
	@BeforeClass
	public void launchBrowser()
	{
		System.out.println("Launch Browser");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashwini\\Automation Jar\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
	
	@BeforeTest
	@Parameters("browser")
    public void launchBrowser(String browser)
	{
		reporter = new ExtentHtmlReporter("test-output"+File.separator+"ExtendReport"+File.separator+"extendReport.html");
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
		
		System.out.println(browser);
		if(browser.equalsIgnoreCase("Chrome")) 
		{
			driver = launchChromeBrowser();
		}
		if(browser.equalsIgnoreCase("Edge")) 
		{
			driver = launchEdgeBrowser();
		}
		 driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 }
		
    @BeforeClass
    public void createPOMObject() {
    	loginPage = new LoginPage(driver);
    	applicationHeader = new ApplicationHeader(driver);
    }
    
   @BeforeMethod
   public void loginToApplication() throws EncryptedDocumentException, IOException {
	   System.out.println("loginToApplication");
	   driver.get("http://localhost/login.do");
	   String useName = Utility.getDataFromExcel("Katraj class", 1, 0);
	   loginPage.sendUserName(useName);	 
	   
	   String password = Utility.getDataFromExcel("Katraj class", 1, 1);
	   loginPage.sendPassword(password);
	   
	   loginPage.clickOnLogin();
		
}
   
	
	@Test
	public void toVerifyTaskButton() 
	{
		testID = 101;
		System.out.println("ToVerifyTaskButton");
		applicationHeader.openTaskPage();
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		
		//expected is equals to actual the test PASS
		//expected is not equals to actual the test FAIL
		Assert.assertEquals(url, "http://localhost/tasks/otasklist.do", "url of task is not found");
		Assert.assertEquals(title, "actiTIME -  Open Tasks", "title of is not found");			//expected is equals to actual the test FAIL			//expected is not equals to actual the test PASS

	}
	@Test
	public void toVerifyUserButton() {
		testID = 102;
		System.out.println("To verify user button");
		applicationHeader.openUserPage();
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		Assert.assertEquals(url, "http://localhost/administration/userlist.do", "url of user is not found");
		Assert.assertEquals(title, "actiTIME -  User List ", "title of user is not found");
		
	}
	
	    @AfterMethod
	    public void logoutFromApplication(ITestResult result) throws IOException {
	    	if(ITestResult.SUCCESS == result.getStatus())
	    	{
	    		Utility.getScreenshot(driver, testID);
	    	}
		System.out.println("logout");
		applicationHeader.logoutFromApplication();
      	}
	    
	@AfterClass
	public void clearPOMOObject() {
		loginPage = null;
		applicationHeader = null;
				
	}
   	@AfterTest
	    public void closeBrowser() {
		System.out.println("close browser");
		driver.quit();
		driver = null;
		System.gc();
	}	  
}


