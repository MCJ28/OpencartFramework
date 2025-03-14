package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.baseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter reporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String timestamp;
	public String reportName;

	@Override
	public void onStart(ITestContext context) 
	{
		 timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
         reportName = "Test-Report-"+timestamp+".html";
         
         reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+reportName);
         
         reporter.config().setDocumentTitle("Test Execution Report");
         reporter.config().setReportName("Automation Test Report");
         reporter.config().setTheme(Theme.DARK);
         
         extent = new ExtentReports();
         extent.attachReporter(reporter);
         
         extent.setSystemInfo("Application", "OpenCart");
         extent.setSystemInfo("Tested By", System.getProperty("user.name"));
         
         String OS = context.getCurrentXmlTest().getParameter("os");
         extent.setSystemInfo("Operating System", OS);
         
         String BR = context.getCurrentXmlTest().getParameter("browser");
         extent.setSystemInfo("Browser", BR);
         
         List<String> inc_groups = context.getCurrentXmlTest().getIncludedGroups();
         if(!inc_groups.isEmpty())
         {
        	 extent.setSystemInfo("Groups", inc_groups.toString());
         }
	}
	
	
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" Test got passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" Test got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			baseClass bc = new baseClass();
			String imgPath = bc.captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" Test got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}


	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
	
}
