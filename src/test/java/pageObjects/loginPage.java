package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage
{
	public loginPage(WebDriver driver) 
	{
		super(driver);	
	}
	
	
	//locator
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement text_Email;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement text_Password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement text_Login;
	
	
	//ActionMethods
	
	public void email(String Email)
	{
		text_Email.sendKeys(Email);
	}
	
	public void password(String pwrd)
	{
		text_Password.sendKeys(pwrd);
	}
	
	public void Click()
	{
		text_Login.click();
	}
}
