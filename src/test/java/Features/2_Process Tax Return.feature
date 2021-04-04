Feature: [SUC:09-06]-Process Tax Return

  @SUC:09-06 @UAT_M4-06-01 @UAT_M4-02-11
  Scenario Outline: UAT_M4-06-01-Verify the Process of Process Tax Return <ReturnDocument>
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Select return document as "<ReturnDocument>"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "<Tin>" and period number as "<Period>" and year as "<Year>"
    Then Click search
#    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[7]"
#    Then Click continue "SearchForm:j_id14"
    Then Fill in declaration fields "<ReturnDocument>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"

    Examples:
      | ReturnDocument              | Year | Period | Tin        |
      | CIT Return (Provisional)    | 2020 | 1      | 1000025802 |
      | CIT Return (Final)          | 2020 | 1      | 1000026000 |
      | Capital Gains Tax Return    |      |        | 1000024202 |
      | Excise Tax Return           | 2020 | 10     | 1000024202 |
      | FTT Return                  | 2020 | 10     | 1000024202 |
      | GST Return                  | 2020 | 9      | 1000024202 |
      | PAYE Returns                | 2020 | 9      | 1000024202 |
      | PIT Return (Provisional)    | 2020 | 1      | 1000024202 |
      | PIT Return (Final)          | 2020 | 1      | 1000025004 |
      | Payroll Tax Return          | 2020 | 9      | 1000024202 |
      | Rental income Return        | 2020 | 9      | 1000024202 |
      | WHT (10.5% and 5.5%) Return | 2020 | 9      | 1000024202 |


#  @SUC:09-06 @UAT_M4-06-02
#  Scenario: UAT_M4-06-02-Verify the Process of Calculation Error
#    Given Open trips URL
#    Then Login as Revenue Officer
#      | tripsuser | Passw0rd |
#    And Click on return filing and processing > File return
#    Then Select return document as "PAYE Returns"
#    Then Click next "FormSelection:nextReturnButton"
#    Then Enter tin as "" and period number as "" and year as ""
#    Then Click search
#    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[7]"
#    Then Click continue "SearchForm:j_id14"
#    Then Fill in declaration fields name as "DR HAMISI", designation as "Software developer", declaration date as "01/01/2018"
#    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
#    Then Verify save success message "Record successfully saved with reference number"

  @SUC:09-06 @UAT_M4-06-03
  Scenario: UAT_M4-06-03-Verify the Process of Log Error Details
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > File return
    Then Select return document as "PAYE Returns"
    Then Click next "FormSelection:nextReturnButton"
    Then Enter tin as "" and period number as "" and year as ""
    Then Click search
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[7]"
    Then Click continue "SearchForm:j_id14"
    Then Fill declaration name field with error
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify error message "Declarant Name contains invalid data"
    Then Click Save with errors
    Then Verify save success message "Record successfully saved with error with reference number"

