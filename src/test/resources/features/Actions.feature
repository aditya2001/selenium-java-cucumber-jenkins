
Feature: Perform different actions on the page

  Background:
    Given A web browser is at heroku app

 @ignore
  Scenario: 1.Select drop down value
    When user clicks on drop down button
    And user select drop down value with
      | value    |
      | Option 1 |
    Then validate drop value displayed

  @ignore
  Scenario: 2. Confirm the popup alerts
    When user clicks on java script alert popups
    And confirm java script alert popups

  @ignore
  Scenario: 3 Close windows add popup
    When user clicks on Entry Add window popup
    And close window add popup
    Then click on re-enable button

  @ignore
  Scenario: 4 Click on multiple windows
    When user clicks on multiple windows button
    And user navigates to new window opened and print title
    Then navigate back to previous window









