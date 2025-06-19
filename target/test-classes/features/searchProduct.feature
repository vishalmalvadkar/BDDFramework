Feature: Verify the search functionality

  Scenario: Validate the search box of app
    Given User is on homepage
    When User enter "mobile" in searchbox
    Then User should get all result