Feature: [SUC:09-12]-Find Taxpayer

  @SUC:09-12 @UAT_M4-12-01
  Scenario Outline: UAT_M4-12-01-Verify the fields in Find Taxpayer Returns screen
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Verify fields dropdown "FormSelection:returnType" and next button "FormSelection:nextReturnButton" and cancel button "FormSelection:Cancel"
    Then Select return document as "PAYE Tax Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Verify presence of fields tin "SearchForm:tin" and period number "SearchForm:periodnumber" and period year "SearchForm:periodyear" and search button "SearchForm:j_idt42" and cancel button "SearchForm:Cancel" and continue button "SearchForm:j_id14" in find return page
    Then Verify table columns "<Tin>" and "<TaxpayerName>" and "<ReturnType>" and "<Period>" and "<DocumentReference>" and "<SubmissionDate>"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Verify readonly fields "<TinField>" and "<TaxPayerNameField>" and "<TradingNameField>" and "<PostalAddressField>" and "<EmailAddressField>" and "<MobileNumberField>" and "<PeriodFromField>" and "<PeriodToField>"
    Then Verify existence of buttons "<Add>" and "<Update>" and "<View>" and "<Remove>" and "<Upload>" and "<DownloadTemplate>" in paye tax return
    Examples:
      | Tin | TaxpayerName  | ReturnType  | Period | DocumentReference  | SubmissionDate  | TinField               | TaxPayerNameField               | TradingNameField               | PostalAddressField               | EmailAddressField               | MobileNumberField              | PeriodFromField                     | PeriodToField                     | Add                              | Update                           | View                             | Remove                           | Upload                           | DownloadTemplate                 |
      | TIN | Taxpayer Name | Return Type | Period | Document Reference | Submission Date | FlexibleFormEntity:TIN | FlexibleFormEntity:TaxpayerName | FlexibleFormEntity:TradingName | FlexibleFormEntity:PostalAddress | FlexibleFormEntity:EmailAddress | FlexibleFormEntity:TelephoneNo | FlexibleFormEntity:PeriodFrom_input | FlexibleFormEntity:PeriodTo_input | FlexibleFormEntity:T_Items:j_id1 | FlexibleFormEntity:T_Items:j_id2 | FlexibleFormEntity:T_Items:j_id3 | FlexibleFormEntity:T_Items:j_id4 | FlexibleFormEntity:T_Items:j_id5 | FlexibleFormEntity:T_Items:j_id6 |

  @SUC:09-12 @UAT_M4-12-02
  Scenario Outline: UAT_M4-12-02-Verify the fields in Find Taxpayer Returns screen
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Select return document as "PAYE Tax Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[1]"
    Then Click continue "SearchForm:j_id14"
    Then Verify readonly fields "<TinField>" and "<TaxPayerNameField>" and "<TradingNameField>" and "<PostalAddressField>" and "<EmailAddressField>" and "<MobileNumberField>" and "<PeriodFromField>" and "<PeriodToField>"
    Then Verify existence of buttons "<Add>" and "<Update>" and "<View>" and "<Remove>" and "<Upload>" and "<DownloadTemplate>" in paye tax return
    Examples:
      | TinField               | TaxPayerNameField               | TradingNameField               | PostalAddressField               | EmailAddressField               | MobileNumberField              | PeriodFromField                     | PeriodToField                     | Add                              | Update                           | View                             | Remove                           | Upload                           | DownloadTemplate                 |
      | FlexibleFormEntity:TIN | FlexibleFormEntity:TaxpayerName | FlexibleFormEntity:TradingName | FlexibleFormEntity:PostalAddress | FlexibleFormEntity:EmailAddress | FlexibleFormEntity:TelephoneNo | FlexibleFormEntity:PeriodFrom_input | FlexibleFormEntity:PeriodTo_input | FlexibleFormEntity:T_Items:j_id1 | FlexibleFormEntity:T_Items:j_id2 | FlexibleFormEntity:T_Items:j_id3 | FlexibleFormEntity:T_Items:j_id4 | FlexibleFormEntity:T_Items:j_id5 | FlexibleFormEntity:T_Items:j_id6 |


# @SUC:09-12 @UAT_M4-12-03
# Scenario: UAT_M4-12-03-Verify the Process of  Validation Failed
#  Given Open trips URL
#  Then Login as Revenue Officer
#   | tripsuser | Passw0rd |
#  And Click on return filing and processing > File return
#  Then Select return document as "PAYE Tax Return"
#  Then Click next "FormSelection:nextReturnButton"
#  Then Enter tin as "" and period number as "1" and year as "2019"
#  Then Click search
#  Then Verify error message "ErrorMessage"

  @SUC:09-12 @UAT_M4-12-04
  Scenario: UAT_M4-12-04-Verify the Process of Returns Not Found
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Select return document as "PAYE Tax Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "P0024640" and period number as "1" and year as "2019"
    Then Click search
    Then Verify no data is found in table