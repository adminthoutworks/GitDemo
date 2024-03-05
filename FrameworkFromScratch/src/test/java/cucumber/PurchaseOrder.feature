Feature: Purchase the order from ecommerce website

  Background:
  Given I landed on ecommerce page

  @Regression
  Scenario Outline: Positive test of purchasing order
    Given  Logged in with username <userName> and password <password>
    When   I add product <productName> to cart
    And    Checkout <productName> and submit the order
    Then   "THANKYOU FOR THE ORDER." message is displayed on confirmation page
    Examples:
      | userName                  | password     | productName     |
      | john.smith@example.com    | testUser@123 | ZARA COAT 3     |
      | angela.gross@testuser.com | testUser@123 | ADIDAS ORIGINAL |