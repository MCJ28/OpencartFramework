package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountConfirmationPage extends basePage
{
	public AccountConfirmationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locator
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	WebElement account_success;
	
	//Action Method
	public String Account_Created()
	{
		try
		{
			return account_success.getText();
		}
		
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
}
