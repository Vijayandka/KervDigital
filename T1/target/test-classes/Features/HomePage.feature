Feature: To test job submission on Kerv

  Scenario: To submit job for UX Designer
    Given The application should be opened in the firefox browser
    When I click on Accept all cookies
    Then I click on careers menu and then job opportunities
    And I select job "UX Designer" at location "United Kingdom"
    Then The UX Designer role details opened in new tab
    When I switch tab
    Then I capture details
    And I click submit
	 Then Close the browser
