package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginConfirmationPage;
import pageObjects.loginPage;
import testBase.baseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends baseClass
{
	@Test(dataProvider="data", dataProviderClass=DataProviders.class, groups = {"dataDriven", "Master"})
	public void DDTTest(String Email, String Password, String exp_Result)
	{
		try
		{
			homePage hp = new homePage(driver);
			hp.account();
			hp.login();
			
			
			loginPage lp = new loginPage(driver);
			lp.email(Email);
			
			
			lp.password(Password);
			
			
			lp.Click();
			
			
			loginConfirmationPage lcp = new loginConfirmationPage(driver);
			boolean result = lcp.MyAccountExists();
			
			/* 
			  	Data valid -->	login successful --> testPass --> logout
			 					login unsuccessful --> testFail
			 
		 		Data invalid -->	login successful --> testFail --> logout
			 						login unsuccessful --> testPass
			 */
			
			
			if(exp_Result.equalsIgnoreCase("valid"))
			{
				if(result==true)
				{
					lcp.Logout();
					Assert.assertTrue(true);
				}
				
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp_Result.equalsIgnoreCase("invalid"))
			{
				if(result==true)
				{
					
					Assert.assertTrue(false);
				}
				
				else
				{
					lcp.Logout();
					Assert.assertTrue(true);
				}
			}
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
	}
}
