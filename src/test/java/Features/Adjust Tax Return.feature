Feature: [SUC:09-10] Adjust Tax Return

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > adjust return

  @SUC:09-10  @UAT_M4-10-01 @UAT_M4-10-02 @UAT_M4-08-02 @UAT_M4-08-05
  Scenario Outline: UAT_M4-10-02 Verify the Process of Return Adjustment-PAYE
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Modify PAYE calculations if not nil
    Then Select reason for amendment as "INCREASE VAT PENALTIES - DEBIT"
    Then Fill in declaration fields name as "Dr Wangari", designation as "Agricultural Engineer", declaration date as "09/01/2020"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Tax return has been successfully saved.The status is now pending approval"
    Examples:
      | ReturnDocument  |
      | PAYE Tax Return |


  @SUC:09-10  @UAT_M4-10-03 @BR01
  Scenario Outline: UAT_M4-10-03 Verify the Process of  Data Errors
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Examples:
      | ReturnDocument      |
      | PAYE Tax Return     |
      | Domestic VAT Return |

  @SUC:09-10  @UAT_M4-10-04
  Scenario Outline: UAT_M4-10-04 Verify the Process of  Save With Errors
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Modify PAYE calculations if not nil
    #    Then Select reason for amendment as "INCREASE VAT PENALTIES - DEBIT"
    Then Fill in declaration fields name as "Dr Wangari9(&*^%", designation as "Agricultural Engineer", declaration date as "09/01/2020"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Then Click Save with errors
    Then Verify save success message "Record successfully saved with reference number"
    Examples:
      | ReturnDocument  |
      | PAYE Tax Return |

  @SUC:09-10  @UAT_M4-10-05
  Scenario Outline: UAT_M4-10-05 Verify the Process of Data Not Saved
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Select reason for amendment as "INCREASE VAT PENALTIES - DEBIT"
    Then Fill in declaration fields name as "Dr Wangari9(&*^%", designation as "Agricultural Engineer", declaration date as "09/01/2020"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Then Click Cancel "FlexibleFormEntity:Cancel"
    Examples:
      | ReturnDocument      |
      | Domestic VAT Return |


  @SUC:09-10  @UAT_M4-10-06
  Scenario Outline: UAT_M4-10-06 Verify the Process of Re-Set
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Select reason for amendment as "INCREASE VAT PENALTIES - DEBIT"
    Then Fill in declaration fields name as "Dr Wangari", designation as "Agricultural Engineer", declaration date as "09/01/2020"
    Then Click reset
    Then Verify name field has been reset "<Name>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save with errors button is displayed
    Then Click Cancel "FlexibleFormEntity:Cancel"
    Examples:
      | ReturnDocument      | Name       |
      | Domestic VAT Return | Dr Wangari |