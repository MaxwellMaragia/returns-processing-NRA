Feature: [SUC:09-10] Adjust Tax Return

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > adjust return

  @SUC:09-10 @UAT_M4-10-01 @UAT_M4-10-02 @UAT_M4-08-02 @UAT_M4-08-05
  Scenario Outline: UAT_M4-10-02 Verify the Process of Return Adjustment for <ReturnDocument>
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "<Tin>" and period number as "<Period>" and year as "<Year>"
    Then Click search
    Then Fill in declaration fields name as "Dr Wangari", designation as "Agricultural Engineer", declaration date as "09/01/2020" for taxtype "<ReturnDocument>"
    #Then Select reason for amendment as "INCREASE VAT PENALTIES - DEBIT"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Confirm saved success message "Tax return has been successfully saved.The status is now pending approval."
    Then Obtain reference number "Tax return has been successfully saved.The status is now pending approval."
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
#      | GST Return                  | 2020 | 9      | 1000024202 |
#      | PAYE Returns                | 2020 | 9      | 1000024202 |
      | PIT Return (Final)          | 2020 | 1      | 1000025004 |
      | Payroll Tax Return          | 2020 | 9      | 1000024202 |
#      | Rental income Return        | 2020 | 9      | 1000024202 |
#      | WHT (10.5% and 5.5%) Return | 2020 | 9      | 1000024202 |

  @SUC:09-10  @UAT_M4-10-03 @BR01
  Scenario Outline: UAT_M4-10-03 Verify the Process of Data Errors
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields name as "Dr Wangari<K9888>?**", designation as "Agricultural Engineer", declaration date as "09/01/2020" for taxtype "<ReturnDocument>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Examples:
      | ReturnDocument |
      | PAYE Returns   |
#      | Domestic VAT Return |

  @SUC:09-10  @UAT_M4-10-04
  Scenario Outline: UAT_M4-10-04 Verify the Process of Save With Errors
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields name as "Dr Wangari>?{}+_))jHJ&", designation as "Agricultural Engineer", declaration date as "09/01/2020" for taxtype "<ReturnDocument>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Then Click Save with errors
    Then Verify save success message "Record successfully saved with reference number"
    Examples:
      | ReturnDocument |
      | PAYE Returns   |

  @SUC:09-10  @UAT_M4-10-05
  Scenario Outline: UAT_M4-10-05 Verify the Process of Data Not Saved
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields name as "Dr Wangari#$%%^^^", designation as "Agricultural Engineer", declaration date as "09/01/2020" for taxtype "<ReturnDocument>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Then Click Cancel "FlexibleFormEntity:Cancel"
    Examples:
      | ReturnDocument |
      | PAYE Returns   |


  @SUC:09-10  @UAT_M4-10-06
  Scenario Outline: UAT_M4-10-06 Verify the Process of Re-Set
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields name as "Dr Wangari", designation as "Agricultural Engineer", declaration date as "09/01/2020" for taxtype "<ReturnDocument>"
    Then Click reset
    Then Verify name field has been reset "<Name>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Then Click Cancel "FlexibleFormEntity:Cancel"
    Examples:
      | ReturnDocument | Name       |
      | PAYE Returns   | Dr Wangari |