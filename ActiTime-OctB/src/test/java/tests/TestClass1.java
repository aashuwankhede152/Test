package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pom.ApplicationHeader;
import pom.LoginPage;

public class TestClass1 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashwini\\Automation Jar\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.get("http://localhost/login.do");
    
        LoginPage loginPage =  new LoginPage(driver);
        loginPage.sendUserName();
        loginPage.sendPassword();
        loginPage.clickOnLogin();
       
        ApplicationHeader applicationHeader = new ApplicationHeader(driver);
    		  applicationHeader.openReportpage();
    		  applicationHeader.logoutFromApplication();
}
}