package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass 
{
	public static WebDriver driver;
	public Logger logger;
	public FileInputStream fis;
	public Properties pr;
	public String timestamp;
	
	
	@Parameters({"os","browser"})
	@BeforeClass(groups = {"Sanity", "Regression", "Master", "dataDriven"})
	public void setUp(String os, String brows) throws IOException
	{
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		pr = new Properties();
		pr.load(fis);
		
		
		if(pr.getProperty("Excecution-environment").equalsIgnoreCase("remote"))
		{
			
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			
			
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			
			else
			{
				System.out.println("No matching Operating System");
				return;
			}
			
			//browser
			switch(brows.toLowerCase())
			{
				case "chrome" : capabilities.setBrowserName("chrome"); break;
				case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
				
				default : System.out.println("No matching Browser"); return;
			}
			
			String hubURL = "http://localhost:4444/wd/hub";
			WebDriver driver = new RemoteWebDriver(new URL(hubURL), capabilities);
		}
		
		
		if(pr.getProperty("Excecution-environment").equalsIgnoreCase("local"))
		{
			switch(brows.toLowerCase())
			{
				case "chrome" : driver = new ChromeDriver(); break;
				case "edge" : driver = new EdgeDriver(); break;
				case "firefox" : driver = new FirefoxDriver(); break;
				default : System.out.println("Invalid Browser name"); return;	//return means, it will exit from execution itself
			}
		}
		
		
		logger = LogManager.getLogger(this.getClass());
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(pr.getProperty("appUrl"));
	}
	
	@AfterClass(groups = {"Sanity", "Regression", "Master", "dataDriven"})
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(7);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String generatedStringANDNumner()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		
		return (generatedString+"*^$#@"+generatedNumber);
	}
	
	public String captureScreen(String tname)
	{
		timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		String Targetfile = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
		File file = new File(Targetfile);
		sourceFile.renameTo(file);
		
		return Targetfile;
	}
}	