Feature: [SUC:09-01]-Lodge Paper Return

  @SUC:09-01 @UAT_M4-01-01 @BR01
  Scenario Outline: UAT_M4-01-01-Verify the fields in Lodge Paper Return (FOF)
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > Lodge return
    Then Verify input fields "<Tin>" and "<TaxpayerName>" and "<TradingName>" and "<ReturnDocument>" and "<Period>" and "<LodgementDate>" and "<SaveButton>" and "<cancelButton>" in lodge return page
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
    Then Verify error message "Tin - Value required."
    Then Verify error message "Return Document - Value required."
    Then Verify error message "Liability - Value required."
    Then Click Return document search button
    Then Switch to frame
    Then Verify input fields "<TinDocumentSearch>" and "<PeriodNumber>" and "<PeriodYear>" in document search frame
    Then Verify searchform buttons "<search>" and "<cancel>" and "<continue>"
    Then Verify table columns "<TinColumn>" and "<TaxpayerNameColumn>" and "<DocumenttypeColumn>" and "<PeriodNumberColumn>" and "<PeriodYearColumn>" in search document frame
    Then Click Cancel "SearchForm:Cancel"
    Examples:
      | Tin                     | TaxpayerName                     | TradingName                     | ReturnDocument                 | Period                       | LodgementDate                           | SaveButton                     | cancelButton            | TinDocumentSearch | PeriodNumber            | PeriodYear            | search             | cancel            | continue          | TinColumn | TaxpayerNameColumn | DocumenttypeColumn | PeriodNumberColumn | PeriodYearColumn |
      | ReturnsLodgement:id_Tin | ReturnsLodgement:id_TaxpayerName | ReturnsLodgement:id_TradingName | ReturnsLodgement:id_ReturnType | ReturnsLodgement:id_PeriodId | ReturnsLodgement:id_LodgementDate_input | ReturnsLodgement:SaveLodgement | ReturnsLodgement:Cancel | SearchForm:TIN    | SearchForm:PeriodNumber | SearchForm:PeriodYear | SearchForm:j_idt21 | SearchForm:Cancel | SearchForm:j_id14 | TIN       | Taxpayer Name      | Document Type      | PeriodNumber       | Period Year      |


  @SUC:09-01 @UAT_M4-01-02 @UAT_M4-01-03 @UAT_M4-08-01 @UAT_M4-08-03 @BR01 @BR05 @sanity
  Scenario Outline: UAT_M4-01-02-Verify the process of Lodge Paper Return for <ReturnDocument>
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
#    And Click on return filing and processing > Lodge return
#    Then Click Return document search button
#    Then Switch to frame
#    Then Search for document with by filling Tin as "<Tin>" Return document as "<ReturnDocument>" Period number as "<Period>" and Period year as "<Year>"
#    Then Click search button "SearchForm:j_idt21"
##    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
##    Then Click continue "SearchForm:j_id14"
##    Then Verify nill return check box is present
#    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
#    Then Verify error message "Liability - Value required."
#    Then Enter liability as "9000.00" and date of lodgement as ""
#    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
#    Then Verify save success message "Returns Lodgement is Successful with Reference Number"
#    Then Verify and obtain ARN "<Arn>"
    Then go to taxpayer accounting > taxpayer account inquiry
    Then Search for tin "<Tin>"
    Then Search for taxtype "<Taxtype>"
    Then Verify taxtype data is shown in table "<Taxtype>"
    Then Verify status is "lodged"
    Then Click on case
    Then Verify lodgement screen has data
    Examples:
      | ReturnDocument              | Year | Period | Tin        | Arn   | Taxtype                       |
#      | CIT Return (Provisional)    | 2020 | 1      | 1000025802 | CIRP  | Company Income Tax            |
      | CIT Return (Final)          | 2020 | 1      | 1000024407 | CIRT  | Company Income Tax            |
#      | Capital Gains Tax Return    |      |        | 1000024202 | CGTR  | Capital Gains Tax             |
#      | Excise Tax Return           | 2020 | 10     | 1000024202 | ETRR  | Excise Tax                    |
#      | FTT Return                  | 2020 | 10     | 1000024202 | FTTR  | Foreign Travel Tax            |
#      | GST Return                  | 2020 | 10     | 1000024202 | GSTR  | Goods and Services Tax        |
#      | PAYE Returns                | 2020 | 10     | 1000024202 | PAYER | Pay As You Earn               |
#      | PIT Return (Provisional)    | 2020 | 1      | 1000024202 | PIRP  | Personal Income Tax           |
#      | PIT Return (Final)          | 2020 | 1      | 1000025004 | PIRF  | Personal Income Tax           |
#      | Payroll Tax Return          | 2020 | 10     | 1000024202 | PTRR  | Payroll Tax                   |
#      | Rental income Return        | 2020 | 10     | 1000024202 | RITR  | Rental Income Tax             |


  @SUC:09-01 @UAT_M4-01-04 @BU04 @BU06
  Scenario: UAT_M4-01-04-Verify the process of lodgement failure
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > Lodge return
    Then Click Return document search button
    Then Switch to frame
    #Then Search for document with by filling Tin as "" Return document as "PAYE Tax Return" Period number as "1" and Period year as "2018"
    Then Click search button "SearchForm:j_idt21"
    Then Verify error message "At least one field is required"
    Then Search for document with by filling Tin as "" Return document as "PAYE Returns" Period number as "" and Period year as ""
    Then Click search button "SearchForm:j_idt21"
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[8]/td[4]"
    Then Click continue "SearchForm:j_id14"
    Then Enter liability as "-9000.00" and date of lodgement as ""
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
    Then Verify error message "Liability cannot be negative for the selected Return Type"
#    And Click on return filing and processing > Lodge return
    Then Click Return document search button
    Then Switch to frame
    Then Search for document with by filling Tin as "1000025802" Return document as "PIT Return (Final)" Period number as "1" and Period year as "2020"
    Then Click search button "SearchForm:j_idt21"
    Then Enter liability as "9000.00" and date of lodgement as "today"
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
    Then Verify error message "A Return already submitted for the same Taxpayer and Period is under process"


  @SUC:09-01 @UAT_M4-01-06 @BR02 @BR03 @BR04
  Scenario Outline: UAT_M4-01-06-Verify the process of late lodgement
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > Lodge return
    Then Click Return document search button
    Then Switch to frame
    Then Search for document with by filling Tin as "" Return document as "<ReturnDocument>" Period number as "7" and Period year as "2019"
    Then Click search button "SearchForm:j_idt21"
    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
    Then Click continue "SearchForm:j_id14"
#    Then Enter lodgement date as "21/09/2018"
    Then Enter liability as "9000.00"
    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
#    Then Verify error message "Lodgement date must be later than the period start date"
#    Then Enter liability as "9000.00" and date of lodgement as "12/04/2019"
#    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
#    Then Verify error message "Lodgment Date should not be backdated more than allowable limits."
#    Then Click Return document search button
#    Then Switch to frame
#    Then Search for document with by filling Tin as "" Return document as "<ReturnDocument>" Period number as "1" and Period year as "2019"
#    Then Click search button "SearchForm:j_idt21"
#    Then Click table column "//*[@id='SearchForm:resultsDataTable_data']/tr[1]/td[4]"
#    Then Click continue "SearchForm:j_id14"
#    Then Enter liability as "9000.00" and date of lodgement as "today"
#    Then Click Submit: xpath "//*[@id='ReturnsLodgement:SaveLodgement']"
    Then Verify save success message "Returns Lodgement is Successful with Reference Number"
    Then Verify save success message "Returns Lodgement is Late"
    Examples:
      | ReturnDocument |
      | PAYE Returns   |


  @SUC:09-01 @UAT_M4-01-07
  Scenario: UAT_M4-01-07-Verify the process of document type not found
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click on return filing and processing > Lodge return
    Then Click Return document search button
    Then Switch to frame
    Then Search for document with only Tin as "P0019361"
    Then Click search button "SearchForm:j_idt21"
    Then Verify no data is found in table


