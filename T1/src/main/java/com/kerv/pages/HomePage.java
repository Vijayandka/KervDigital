package com.kerv.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.kerv.pages.JobDetails;
import com.kerv.base.AppBase;


public class HomePage{

	protected WebDriver driver;

	//Locators
	private By acceptCookie = By.xpath("//div[@id='BorlabsCookieBox']//a[contains(text(),'Accept all')]");
	private By careerMenu = By.xpath("//div[@data-id='18659']");
	private By jobOpportunity = By.xpath("//a[text()='Job Opportunities']");
	private By jobRole = By.xpath("//a[@href='https://kerv.com/job-details/mp024o5']");
	
	//Constructors
	public HomePage(WebDriver driver){
		this.driver = driver;
	}

	
	public void acceptCookies()
	{
		AppBase.logger.info("Clicking on Accept cookies");
		try
		{
           //Accepting all cookies
			WebElement cookieElement = driver.findElement(acceptCookie);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(cookieElement)).click();
			wait.until(ExpectedConditions.invisibilityOf(cookieElement));
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during accepting cookies"  + ex.getMessage());
		}
	}

	public void clickJobOpportunities()
	{
		AppBase.logger.info("Clicking on Career->Job Opportunities");
		try
		{
			//Clicking Careers
			Actions actions = new Actions(driver); 			
			WebElement menuCareer = driver.findElement(careerMenu);
			menuCareer.click();
			//Clicking job opportunities
			WebElement jOpportunity = driver.findElement(jobOpportunity);
			actions.moveToElement(jOpportunity);
			jOpportunity.click();
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during selection of careerMenu->JobOppurtunity"  + ex.getMessage());
		}
	}
	
	public void selectJob()
	{
		AppBase.logger.info("Clicking on Job with name UX Designer");
		try
		{
			//Scroll to job role
			WebElement element = driver.findElement(jobRole);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(2000);/*Instead of Thread.sleep, explicit wait can be used*/
			//Clicking job role
			driver.findElement(jobRole).click();
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during selection of careerMenu->JobOppurtunity and job=UX Designer " + ex.getMessage());
		}
	}
	
	public JobDetails switchToJobDetail()
	{
		JobDetails objJobDetails = null;
		//Get parent window handle
		String parentHandle = driver.getWindowHandle(); 
		try
		{
			//Get all window handles and switch to newly opened tab
			for (String winHandle : driver.getWindowHandles()) {
		        if(!parentHandle.equals(winHandle))
		        {
		        	driver.switchTo().window(winHandle);
		        }
		    }
			//Creating an object for job details page
			objJobDetails = new JobDetails(driver);
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during switching to Job detail page " + ex.getMessage());
		}
		return objJobDetails;
	}
}
