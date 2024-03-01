Feature: Error validation


  @ErrorValidation
  Scenario Outline: Validate Login functionality with invalid credentials
    Given I landed on ecommerce page
    When  Logged in with username <userName> and password <password>
    Then  "Incorrect email or password." message is displayed
    Examples:
      | userName               | password    |
      | john.smith@example.com | testUser@ |

  @ErrorValidation
  Scenario Outline: Validate Login functionality with invalid credentials for user2
    Given I landed on ecommerce page
    When  Logged in with username <userName> and password <password>
    Then  "Incorrect email or password." message is displayed
    Examples:
      | userName               | password    |
      | angela.gross@testuser.com | testUser@ |
