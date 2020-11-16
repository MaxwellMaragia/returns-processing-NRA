Feature: [SUC:09-02]-Process Paper Form

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return

  @SUC:09-02 @UAT_M4-02-06
  Scenario Outline: UAT_M4-02-06-Verify the Process Paper Form-PAYE Return
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[2]/td[7]"
    Then Click continue "SearchForm:j_id14"
    Then Click add "FlexibleFormEntity:T_Items:j_id1"
    Then Switch to frame
    Then Click search button "TestFlexibleForm:j_id3"
    Then Switch to default
    Then Switch to frame 2
    Then Find entity by Tin "P0017167"
    Then Click search button "SearchForm:j_idt42"
    Then Switch to frame
#    Then Enter designation as "Software developer" and basic salary as "245000"
    Then Fill in PAYE details
    Then Click ok
    Then Fill in declaration fields name as "DR HAMISI", designation as "Software developer", declaration date as "01/01/2018"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"
    Examples:
      | ReturnDocument |
#      | Capital Gain Tax(CGT) Return    |
#      | Company Income Tax(CIT) Return  |
#      | Dividend Tax Return             |
#      | Domestic Excise Return          |
#      | Domestic VAT Return             |
#      | Fringe Benefit Tax Return       |
#      | Non Resident Tax(NRT) Return    |
#      | Fringe Benefit Tax Return       |
      | PAYE Returns   |
#      | Personal Income Tax(PIT) Return |
#      | Provisional Tax(CIT) Return     |
#      | Provisional Tax(PIT) Return     |
#      | Turnover Tax(TOT) Return        |
#      | Withholding Tax(WHT) Return     |


#  @SUC:09-02 @UAT_M4-02-11
#  Scenario: UAT_M4-02-11-Verify the Process Paper Form-VAT Return
#    Then Select return document as "Domestic VAT Return"
#    Then Click next "FormSelection:nextReturnButton"
#    Then Enter tin as "" and period number as "" and year as "2019"
#    Then Click search
#    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[1]"
#    Then Click continue "SearchForm:j_id14"
#    Then Fill in Transaction details
#    Then Click add "FlexibleFormEntity:TIOnInpVatLocPur_Table:j_id1"
#    Then Switch to frame
#    Then Enter transaction information for local purchases: date "01/07/2020", inv number "123", VAT "100000"
#    Then Click ok
#    Then Fill in declaration fields name as "DR HAMISI", designation as "Software developer", declaration date as "01/01/2018"
#    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
#    Then Verify save success message "Record successfully saved with reference number"

  @SUC:09-02 @UAT_M4-02-16
  Scenario: UAT_M4-02-16-Verify the Process Paper Form-Zero VAT Return
    Then Select return document as "Domestic VAT Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "C0020105" and period number as "" and year as "2020"
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[7]"
    Then Click continue "SearchForm:j_id14"
    Then Select nill return "//*[@id='FlexibleFormEntity:NilReturn']"
    Then Verify numeric fields are readonly and total is zero
    Then Fill in declaration fields name as "DR HAMISI", designation as "Software developer", declaration date as "01/01/2018"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"

  @SUC:09-02 @UAT_M4-02-17 @BR02 @B203
  Scenario: UAT_M4-02-17-Verify the Process of Resolve Errors
    Then Select return document as "PAYE Tax Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as "2019"
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]"
    Then Click continue "SearchForm:j_id14"
    Then Click add "FlexibleFormEntity:T_Items:j_id1"
    Then Switch to frame
    Then Click search button "TestFlexibleForm:j_id3"
    Then Switch to default
    Then Switch to frame 2
    Then Find entity by Tin "C0018547"
    Then Click search button "SearchForm:j_idt42"
    Then Switch to frame
    Then Click ok
    Then Verify error message "1. Designation: Validation Error: Value is required."
    Then Enter designation as "Software developer" and basic salary as "245000"
    Then Click ok
    Then Fill in declaration fields name as "DR HAMISI", designation as "Software developer", declaration date as "01/01/2018"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"

  @SUC:09-02 @UAT_M4-02-18
  Scenario: UAT_M4-02-18-Verify the Process of Save with Errors
    Then Select return document as "Domestic VAT Return"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[1]"
    Then Click continue "SearchForm:j_id14"
    #    Then Click add "FlexibleFormEntity:T_Items:j_id1"
    #    Then Switch to frame
    #    Then Click search button "TestFlexibleForm:j_id3"
    #    Then Switch to default
    #    Then Switch to frame 2
    #    Then Find entity by Tin "P0020180"
    #    Then Click search button "SearchForm:j_idt42"
    #    Then Switch to frame
    #    Then Click ok: xpath "//*[@id='TestFlexibleForm:Save']"
    #    Then Verify error message "1. Designation: Validation Error: Value is required."
    #    Then Enter designation as "Software developer" and basic salary as "245000"
    #    Then Click ok: xpath "//*[@id='TestFlexibleForm:Save']"
    Then Fill in declaration fields name as "DR HAMISI*&^^%%^^&'\", designation as "Software developer", declaration date as "01/01/2018"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify error message "Declarant Name contains invalid data"
    Then Click Save with errors
    Then Verify save success message "Record successfully saved with error with reference number"