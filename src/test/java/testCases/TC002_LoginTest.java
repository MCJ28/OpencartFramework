package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginConfirmationPage;
import pageObjects.loginPage;
import testBase.baseClass;

public class TC002_LoginTest extends baseClass
{
	@Test(groups={"Sanity", "Master"})
	public void loginTest()
	{
		try
		{
			logger.info("TC002_LoginTest execution started");
			
			homePage hp = new homePage(driver);
			hp.account();
			hp.login();
			logger.info("login page opened");
			
			loginPage lp = new loginPage(driver);
			lp.email(pr.getProperty("Email"));
			logger.info("Email address enetred");
			
			lp.password(pr.getProperty("Password"));
			logger.info("Password enetred");
			
			lp.Click();
			logger.info("Clicked on Login button");
			
			loginConfirmationPage lcp = new loginConfirmationPage(driver);
			boolean result = lcp.MyAccountExists();
			
			Assert.assertTrue(result);
			logger.info("TC002_LoginTest execution finished & user succesfully loggedin to the application");
		}
		
		catch(Exception e)
		{
			logger.info("TC002_LoginTest failed");
			Assert.assertTrue(false);
		}
	}
}
