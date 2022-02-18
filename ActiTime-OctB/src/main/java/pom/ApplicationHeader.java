package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationHeader {

		@FindBy (xpath = "(//div[@class='label'])[1]")
		private WebElement timeTrack;
		
		@FindBy (xpath = "(//div[@class='label'])[2]")
		private WebElement task;
		
		@FindBy (xpath = "(//div[@class='label'])[3]")
		private WebElement report;
		
		@FindBy (xpath = "(//div[@class='label'])[4]")
		private WebElement user;
		
		@FindBy (xpath = "//a[@class='logout']")
		private WebElement logout;

		//private WebDriver driver;
		public ApplicationHeader(WebDriver driver) 
		{
			PageFactory.initElements(driver, this);
			//this.driver = driver;
		}
		
		public void openTimeTrackPage() {
			timeTrack.click();
		}
		
		public void openTaskPage() {
			task.click();
		}
		
		public void openReportpage() {
			report.click();
		}
		
		public void openUserPage() {
			user.click();
		}
		
		public void logoutFromApplication() {
			logout.click();
		}

		
		}
		
	


