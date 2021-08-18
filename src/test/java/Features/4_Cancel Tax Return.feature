Feature: [SUC:09-11] Cancel Tax Return


  @SUC:09-11 @UAT_M4-11-01 @UAT_M4-11-02 @UAT_M4-08-03 @BR04 @sanity
  Scenario Outline: UAT_M4-11-02 Verify the Process of Return Cancellation (<ReturnDocument>)
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > cancel return
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
    Then Verify ARN for cancel "<Arn>"
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
    Given Open trips URL
    Then go to taxpayer accounting > taxpayer account inquiry
    Then Search for tin "<Tin>"
    Then Search for taxtype "<Taxtype>"
    Then Verify taxtype "<Taxtype>" and status "Cancelled" is shown in table for "<ReturnDocument>"
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


  @SUC:09-11 @UAT_M4-11-03
  Scenario Outline: UAT_M4-11-03 Verify the Process of Abandon Process
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > cancel return
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
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > cancel return
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
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click returns filing and processing > cancel return
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