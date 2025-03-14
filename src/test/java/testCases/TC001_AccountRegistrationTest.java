package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountConfirmationPage;
import pageObjects.homePage;
import pageObjects.registrationPage;
import testBase.baseClass;

public class TC001_AccountRegistrationTest extends baseClass
{
	@Test(groups={"Regression", "Master"})
	public void registerTest()
	{
		try
		{
			logger.info("TC001_AccountRegistrationTest started");
			
			homePage hp = new homePage(driver);
			hp.account();
			logger.info("Clicked on MyAccount dropDown");
			
			hp.register();
			logger.info("Clicked on Register button");
			
			registrationPage page = new registrationPage(driver);
			logger.info("providing user details into application");
			
			page.FirstName(randomString().toUpperCase());
			page.LastName(randomString().toUpperCase());
			page.Email(randomString()+"@gmail.com");
			page.Telephone(randomNumber());
			
			String password = generatedStringANDNumner();
			
			page.Password(password);
			page.ConfirmPassword(password);
			page.PrivacyPolicy();
			page.Continue();
			logger.info("clicked on continue button after providing all the user details into application");
			
			AccountConfirmationPage acc_page = new AccountConfirmationPage(driver);
			String text = acc_page.Account_Created();
			
			
			
			if(text.equals("Your Account Has Been Created!"))
			{
				logger.info("validation message is correct");
				Assert.assertTrue(true);
			}
			
			else
			{
				logger.error("Test case failed due to validation message is incorrect");
				logger.debug("Debug logs");
				
				Assert.assertTrue(false);
			}
		}
		
		catch(Exception e)
		{
			
		}
		
		logger.info("TC001_AccountRegistrationTest Executed and Finished");
	}
}
