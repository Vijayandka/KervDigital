package com.kerv.base;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

/* Base class containing initialization, tear down and login functions */
public class AppBase {

	public static WebDriver driver = null;
	public static WebDriverWait expWait = null;
	private String baseURL="https://kerv.com/about-kerv/kerv-practices/kerv-digital/";
	public static Logger logger = null;
	public static Properties prop = null;

	/**
	 * Initialize all the objects used for the application under test.
	 * 
	 * @author vijaya
	 * 
	 *
	 */
	@SuppressWarnings("deprecation")
	public void appInit()
	{
		try 
		{
			String log4jConfPath = "config/log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
			//initialize logger
			logger = Logger.getLogger(this.getClass());
			
			logger.info("Setting up Gecko driver");
			System.setProperty("webdriver.gecko.driver", "GeckoDriver/geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			//Set your firefox path here
			options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe")));     
			driver = new FirefoxDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			driver.get(baseURL);
		} 
		catch (Exception ex)
		{
			logger.error("Failed to read the config.properties "  + ex.getStackTrace());
		}
	}

	
	/**
	 * Close the browser and flush the details onto extent report
	 * 
	 ** @author 
	 *
	 */
	public void appClose()
	{
		driver.quit();
		logger.info("Closed the browser");
	}
}
