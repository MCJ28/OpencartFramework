package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registrationPage extends basePage 
{
	//constructor
	
	public registrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locator
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement text_Firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement text_Lastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement text_Email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement text_Telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement text_Password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement text_ConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btn_privacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_continue;
	
	//Action Methods
	
	public void FirstName(String FName)
	{
		text_Firstname.sendKeys(FName);
	}
	
	public void LastName(String LName)
	{
		text_Lastname.sendKeys(LName);
	}
	
	public void Email(String email)
	{
		text_Email.sendKeys(email);
	}
	
	public void Telephone(String telPhone)
	{
		text_Telephone.sendKeys(telPhone);
	}
	
	public void Password(String pwrd)
	{
		text_Password.sendKeys(pwrd);
	}
	
	public void ConfirmPassword(String pwrd)
	{
		text_ConfirmPassword.sendKeys(pwrd);
	}
	
	public void PrivacyPolicy()
	{
		btn_privacyPolicy.click();
	}
	
	public void Continue()
	{
		btn_continue.click();
	}
}
