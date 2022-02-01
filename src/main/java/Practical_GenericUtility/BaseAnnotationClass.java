package Practical_GenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Practical_pomrepository.HomePage;
import Practical_pomrepository.LoginPage;


public class BaseAnnotationClass {
	
	
	public WebDriver driver;
	/*object creation*/
	public JavaUtility jLib = new JavaUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public FileUtility fLib = new FileUtility();
	public WebdriverUtility wLib = new WebdriverUtility();
	//public static WebDriver sDriver = null;
	
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS()
	{
		System.out.println("===Connect to Database===");
	}
	
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws Throwable
	{
		System.out.println("===Launch Browser===");
		String BROWSER = fLib.readDatafromPropertyfile("browser");
		String URL = fLib.readDatafromPropertyfile("url");
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		wLib.maximizeWindow(driver);
		wLib.waitForpageLoad(driver);
		driver.get(URL);
		
		
	}

	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable
	{
		System.out.println("===Login to App===");
		String USERNAME = fLib.readDatafromPropertyfile("username");
		String PASSWORD = fLib.readDatafromPropertyfile("password");
		LoginPage login = new LoginPage(driver);
		login.loginToApp(USERNAME, PASSWORD);
		
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("===Logout from App===");
		HomePage home = new HomePage(driver);
		home.Logout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("===Close the Browser===");
		wLib.CloseBrowser(driver);
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS()
	{
		System.out.println("===Disconnect from Database===");
	}
	

}
