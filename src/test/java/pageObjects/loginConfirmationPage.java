package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginConfirmationPage extends basePage
{
	public loginConfirmationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locator
	
	@FindBy(xpath="//h2[text()='My Account']")
	WebElement text_myAccount;
	
	@FindBy(xpath="//a[@class=\"list-group-item\"][13]")
	WebElement btn_Logout;
	
	
	//Action Methods
	
	public boolean MyAccountExists()
	{
		try
		{
			return text_myAccount.isDisplayed();
		}
		
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void Logout()
	{
		btn_Logout.click();
	}
} 
