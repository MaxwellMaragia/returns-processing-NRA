Feature: [SUC:09-15] PAYE Credit

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > paye credit

  @SUC:09-15 @UAT_M4-12-01-paye_credit @UAT_M4-12-02-paye_credit @BR01
  Scenario: UAT_M4-12-02 Verify the Process of Find PAYE Credit
    Then Click find to search for employee
    Then Switch to frame
    Then Search for employee using tin "P0017167"
    Then Search for employer
    Then Verify paye


