package com.contact.contact.e2e;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ContactForm {

    @Given("I am on the contact form page")
    public void i_am_on_the_contact_form_page() {
        // Implement the code to navigate to the contact form page
    }

    @When("I enter the name {string}")
    public void i_enter_the_name(String name) {
        // Implement the code to enter the name in the contact form
    }

    @When("I enter the company {string}")
    public void i_enter_the_company(String company) {
        // Implement the code to enter the company in the contact form
    }

    @When("I enter the number {string}")
    public void i_enter_the_number(String number) {
        // Implement the code to enter the number in the contact form
    }

    @When("I enter the email {string}")
    public void i_enter_the_email(String email) {
        // Implement the code to enter the email in the contact form
    }

    @When("I click the Save button")
    public void i_click_the_save_button() {
        // Implement the code to click the Save button
    }

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        // Implement the code to verify the success message is displayed
    }
}
