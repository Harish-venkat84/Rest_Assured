@CsquareCreateCustomer
Feature: To Create Customer C2
  ------------

  @CsquareCustomerCreateCQ
  Scenario: Csquare Create New Customer CQ
    Given I am on an new customer Create API
    And I create Csquare Customer
    Then I Validate expected response code with 1000
