Feature: Contact Form
  Scenario: Submitting the contact form
    Given I am on the contact form page
    When I enter the name "Sheikh Saad"
    And I enter the company "SDTechnologist"
    And I enter the number "123456789"
    And I enter the email "sheikh@domain.com"
    And I click the Save button
    Then I should see a success message
