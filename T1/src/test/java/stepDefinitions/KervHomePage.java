package stepDefinitions;



import com.kerv.base.*;
import com.kerv.pages.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class KervHomePage {
	
	AppBase appBase;
	HomePage hPage;
	JobDetails jobDetails;
	
    
    @SuppressWarnings("deprecation")
	@Given("^The application should be opened in the firefox browser$")
	public void the_application_should_be_opened_in_the_firefox_browser() throws Throwable {	
		appBase = new AppBase();
		appBase.appInit();
		hPage = new HomePage(appBase.driver);
	}


	@When("^I click on Accept all cookies$")
	public void i_click_on_Accept_all_cookies() throws Throwable {
	    hPage.acceptCookies();
	}

	
	@Then("^I click on careers menu and then job opportunities$")
	public void i_click_on_careers_menu() throws Throwable {
	    hPage.clickJobOpportunities();
	}

	
	@Then("^I select job \"([^\"]*)\" at location \"([^\"]*)\"$")
	public void i_select_job_at_location(String jName, String jCountry) throws Throwable {
	    hPage.selectJob();
	}
	
	
	@Then("^The UX Designer role details opened in new tab$")
	public void the_UX_Designer_role_details_opened_in_new_tab() throws Throwable {
		jobDetails = hPage.switchToJobDetail();
	}

	
	@When("^I switch tab$")
	public void i_switch_tab() throws Throwable {
		jobDetails.scrollToApplyToday();
	}

	
	@Then("^I capture details$")
	public void i_capture_details() throws Throwable {
	    jobDetails.fillCandidateDetails("TEST", "TEST", "Test@test.com", "12345678");
	    jobDetails.uploadResume("C:\\Users\\Vijaya\\Desktop\\MyTests\\T1\\Documents\\UploadDocument.doc");
//	    jobDetails.uploadResume(".\\Documents\\UploadDocument.doc");
	}

	
	@Then("^I click submit$")
	public void i_click_submit() throws Throwable {
		jobDetails.agreeAndApply();
	}

	@Then("^Close the browser$")
	public void close_the_browser()  {
	    appBase.appClose();	
	}

}
