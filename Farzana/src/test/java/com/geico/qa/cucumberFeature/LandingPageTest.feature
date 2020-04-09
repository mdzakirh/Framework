Feature: LandingPage validation

Scenario: Successfully starting quote by entering zipcode.
Given User is on Landing Page
Then Verify the LandingPageTitle
When Use is selecting the autoText box option
When User is entring zipcode
And clicking on start quote button
Then User is able to move to customer information page successfully
 