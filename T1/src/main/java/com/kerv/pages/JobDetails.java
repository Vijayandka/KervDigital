package com.kerv.pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.kerv.base.AppBase;


public class JobDetails{

	protected WebDriver driver;

	//Locators
	private By applyToday = By.xpath("//h3[text()='Apply today']");
	private By fName = By.id("candidate_first_name");
	private By lName = By.id("candidate_last_name");
	private By email = By.id("candidate_email");
	private By phone = By.id("candidate_phone");
	private By resume = By.xpath("//input[@id='resume']");
	private By resumeText = By.xpath("//div[@class='gfield_description']");
	private By submit = By.xpath("//button[contains(@name,'job_application_form')]");
	
	//Constructor
	public JobDetails(WebDriver driver){
		this.driver = driver;
	}

	
	public void scrollToApplyToday()
	{
		AppBase.logger.info("Scrolling to Apply Today");
		try
		{
			//Scroll to Form
			WebElement element = driver.findElement(applyToday);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during scrolling to apply today "  + ex.getMessage());
		}
	}

			
	public void fillCandidateDetails(String cfName, String clName, String cEmail, String cPhone)
	{
		AppBase.logger.info("Filling candidate details");
		try
		{
			//Fill all candidate details
			driver.findElement(fName).sendKeys(cfName);
			driver.findElement(lName).sendKeys(clName);
			driver.findElement(email).sendKeys(cEmail);
			driver.findElement(phone).sendKeys(cPhone);
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during filling candidate details"  + ex.getMessage());
		}
	}
	
	public void uploadResume(String filePath)
	{
		AppBase.logger.info("Uploading resume");
		try
		{
			//Uploading resume
			WebElement resumeT = driver.findElement(resumeText);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",resumeT);
			WebElement eleResult = driver.findElement(resume);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", eleResult);
			Thread.sleep(5000);
			StringSelection stringSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			 Robot robot = new Robot();
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_V);
			 robot.keyRelease(KeyEvent.VK_V);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_TAB);
			 robot.keyRelease(KeyEvent.VK_TAB);
			 Thread.sleep(3000);
			 robot.keyPress(KeyEvent.VK_ENTER);
			 robot.keyRelease(KeyEvent.VK_ENTER);
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during upload Resume"  + ex.getMessage());
		}
	}
	
	public void agreeAndApply()
	{
		AppBase.logger.info("Agree conditions and submit");
		try
		{
			//Scroll to Submit
			WebElement subElement = driver.findElement(submit);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",subElement);
			Thread.sleep(500);
			//Clicking checkboxes
			List<WebElement> lstAgree = driver.findElements(By.xpath("//div[contains(@class,'gchoice')]//input/../label"));
		  	for(WebElement agreeChk : lstAgree)
		  	{
		  		agreeChk.click();
		  	}
		  	//Clicking submit
		  	subElement.click();
		}
		catch(Exception ex)
		{
			AppBase.logger.error("Exception occured during Agree and Apply"  + ex.getMessage());
		}
	}
}
