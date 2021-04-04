Feature: [SUC:09-11] Cancel Tax Return

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > cancel return

  @SUC:09-11 @UAT_M4-11-01 @UAT_M4-11-02 @UAT_M4-08-03 @BR04
  Scenario Outline: UAT_M4-11-02 Verify the Process of Return Cancellation (<ReturnDocument>)
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "<Tin>" and period number as "<Period>" and year as "<Year>"
    Then Click search
#    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
#    Then Click continue "SearchForm:j_id14"
    Then Select reason for cancellation as "RETURN POSTED TO WRONG TAXPAYER" "<ReturnDocument>"
    Then Click cancel return
    Then Click yes
    Then Verify save success message "Tax return has successfully saved.The status is now pending cancellation"
    Then Obtain reference number for cancellation "Tax return has successfully saved.The status is now pending cancellation"
    Given Open CRM URL for Returns Module
    And Close Popup Window
    And Click on Case management dropdown
    And click on Returns Tax return application
    Then switch to frame zero
    When enters reference number in search results
    When Click selected Reference Number
    Then switch to frame one
    And clicks Approve from the dropdown
    And click save on returns
    Then Application Tax return Adjustment status should be "Approved"
    Examples:
      | ReturnDocument              | Year | Period | Tin        |
      | CIT Return (Final)          | 2020 | 1      | 1000026000 |
      | Capital Gains Tax Return    |      |        | 1000024202 |
      | Excise Tax Return           | 2020 | 10     | 1000024202 |
      | FTT Return                  | 2020 | 10     | 1000024202 |
      | GST Return                  | 2020 | 9      | 1000024202 |
      | PAYE Returns                | 2020 | 9      | 1000024202 |
      | PIT Return (Final)          | 2020 | 1      | 1000025004 |
      | Payroll Tax Return          | 2020 | 9      | 1000024202 |
      | Rental income Return        | 2020 | 9      | 1000024202 |
      | WHT (10.5% and 5.5%) Return | 2020 | 9      | 1000024202 |

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
      | ReturnDocument |
      | PAYE Returns   |

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
    Then Verify switch to page with url "http://34.241.245.79:8080/trips-ui/faces/core/GenericSearch.xhtml"
    Examples:
      | ReturnDocument |
      | PAYE Returns   |

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
      | ReturnDocument |
      | PAYE Returns   |