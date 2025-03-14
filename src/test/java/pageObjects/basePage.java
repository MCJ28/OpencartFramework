package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class basePage 
{
	public WebDriver driver;
	
	basePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
