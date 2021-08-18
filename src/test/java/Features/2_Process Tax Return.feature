Feature: [SUC:09-06]-Process Tax Return

  @SUC:09-06 @UAT_M4-06-01 @UAT_M4-02-11 @sanity
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
    Then Fill in declaration fields "<ReturnDocument>" considering if nill return is applicable "<Nill>"
    Then Click Submit: xpath "//*[@id='FlexibleFormEntity:save']"
    Then Verify save success message "Record successfully saved with reference number"
    Then Verify and obtain ARN for file "<Arn>"
    Then go to taxpayer accounting > taxpayer account inquiry
    Then Search for tin "<Tin>"
    Then Search for taxtype "<Taxtype>"
    Then Verify taxtype "<Taxtype>" and status "Posted" is shown in table for "<ReturnDocument>"
    Then Click on case
    Then Verify file returns screen has data for "<ReturnDocument>"
    Examples:
      | ReturnDocument              | Year | Period | Tin        | Arn  | Taxtype                       | Nill |
#      | CIT Return (Provisional)    | 2020 | 1      | 1000009475 | CIRP  | Company Income Tax            |      |
#      | CIT Return (Final)          | 2020 | 1      | 1000009874 | CIRT  | Company Income Tax            |      |
      | CGT Return                  |      |        | 1000062805 | CGTR | Capital Gains Tax             |      |
#      | PIT Return (Provisional)    | 2020 | 1      | 1000009688 | PIRP  | Personal Income Tax           |      |
#      | PIT Return (Final)          | 2020 | 1      | 1000010074 | PIRF  | Personal Income Tax           |      |
      | Excise Tax Return           | 2020 | 1      | 1000063003 | ETRR | Excise Tax                    |      |
      | FTT Return                  | 2020 | 1      | 1000062805 | FTTR | Foreign Travel Tax            |      |
#      | GST Return                  | 2020 | 9      | 1000009475 | GSTR  | Goods and Services Tax        |      |
#      | PAYE Returns                | 2020 | 9      | 1000009475 | PAYER | Pay As You Earn               |      |
      | Payroll Tax Return          | 2020 | 1      | 1000062805 | PTRR | Payroll Tax                   |      |
      | Rental income Return        | 2020 | 1      | 1000062805 | RITR | Rental Income Tax             |      |
      | WHT (10.5% and 5.5%) Return | 2020 | 1      | 1000062805 | WHT  | Withholding Tax(5.5% & 10.5%) |      |



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

