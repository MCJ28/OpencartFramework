package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage
{
	public homePage(WebDriver driver)
	{
		super(driver);
	}
	
	//locator
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement btn_myAccount;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement btn_register;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement btn_login;
	
	
	//Action Methods
	
	public void account()
	{
		btn_myAccount.click();
	}
	
	public void register()
	{
		btn_register.click();
	}
	
	public void login()
	{
		btn_login.click();
	}
}
