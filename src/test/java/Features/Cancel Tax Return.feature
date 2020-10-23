Feature: [SUC:09-11] Cancel Tax Return

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > cancel return

  @SUC:09-11 @UAT_M4-11-01 @UAT_M4-11-02 @UAT_M4-08-03 @BR04
  Scenario Outline: UAT_M4-11-02 Verify the Process of Return Cancellation
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Select reason for cancellation as "RETURN POSTED TO WRONG TAXPAYER" "<ReturnDocument>"
    Then Click cancel return
    Then Click yes
    Then Verify save success message "Tax return has successfully saved.The status is now pending cancellation"
    Examples:
      | ReturnDocument                 |
      | Capital Gain Tax(CGT) Return   |
      | Company Income Tax(CIT) Return |
      | Domestic Excise Return         |
      | Domestic VAT Return            |
      | PAYE Tax Return                |
      | Turnover Tax(TOT) Return       |
      | Withholding Tax(WHT) Return    |


  @SUC:09-11 @UAT_M4-11-03
  Scenario Outline: UAT_M4-11-03 Verify the Process of Abandon Process
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Select reason for cancellation as "RETURN POSTED TO WRONG TAXPAYER" "<ReturnDocument>"
    Then Click cancel return
    Then Click no
    Examples:
      | ReturnDocument  |
      | PAYE Tax Return |

  @SUC:09-11 @UAT_M4-11-04 @BR03
  Scenario Outline: UAT_M4-11-04 Verify the Process of Cancel Abandon
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Select reason for cancellation as "RETURN POSTED TO WRONG TAXPAYER" "<ReturnDocument>"
    Then Click Cancel "FlexibleFormEntity:Cancel"
    Then Verify switch to page with url "https://backoffice.mra.mw:8443/trips-ui/faces/core/GenericSearch.xhtml"
    Examples:
      | ReturnDocument  |
      | PAYE Tax Return |

  @SUC:09-11 @UAT_M4-11-05 @BR01
  Scenario Outline: UAT_M4-11-05 Verify the Process of Validation error
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Click cancel return
    Then Click yes
    Then Verify error message "Reason for Cancellation: Validation Error: Value is required"
    Examples:
      | ReturnDocument  |
      | PAYE Tax Return |