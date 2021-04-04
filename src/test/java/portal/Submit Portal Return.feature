Feature: [SUC:09-14]-Submit Portal Return

#   @UAT_M4-14-01
#  Scenario Outline: UAT_M4-14-01-Verify the fields in Submit Portal Return screens
#    Given Open portal URL
#    Then Login to portal
#      | payereturns | Codei@maseno2020 |
#    And Click on my tax
#    Then Click submit returns
#    Then Select taxtype "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/div[1]/table/tbody/tr[4]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
#    Then Select period "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
#    Then Click continue "btnContinue"
#    Then Verify input fields "<Period>" and "<valueOfSales>" and "<vatOnImportedServices>" and "<vatOnDisposalOfCapitalItems>" and "<valueOfExportSupplies>" and "<valueOfZeroRatedSupplies>" and "<valueOfExemptSupplies>" and "<totalVat>" and "<cancel>" in vat return page
#    Then Fill in fields under VAT Returns with the double "30"
#    Then Click next button "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[3]/div/button[2]"
#    Then Verify download and upload buttons with id "id_vatOnLocalPur_DownloadBtn" and "id_fileChoose"
#    Then Click Cancel "btnCancel"
#    Then Click ok in confirm dialogue
#    Then Logout from portal
#    Given Open portal URL
#    Then Login to portal
#      | maxipain | Codei@maseno2020 |
#    And Click on my tax
#    Then Click submit returns
#    #PAYE
#    Then Select taxtype "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/div[1]/table/tbody/tr[3]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
#    Then Select period "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
#    Then Click continue "btnContinue"
#    Then Verify input fields "<Period>" and "<nillCheckbox>" and "<totalGrossIncome>" and "<totalPaye>" and "<printButton>" and "<canceButton>" and "<submitButton>" in paye
#    Then Click Cancel "btnCancel"
#    Examples:
#      | Period    | nillCheckbox      | valueOfSales | vatOnImportedServices | vatOnDisposalOfCapitalItems | valueOfExportSupplies | valueOfZeroRatedSupplies | valueOfExemptSupplies | totalVat | cancel    | totalGrossIncome    | totalPaye    | printButton | canceButton | submitButton |
#      | id_period | id_payeReturnForm | id_value1    | id_vat2               | id_vat3                     | id_value4             | id_value5                | id_value6             | id_vat7  | btnCancel | id_totalGrossIncome | id_totalPaye | btnPrint    | btnCancel   | btnSave      |

#   @UAT_M4-14-02
#  Scenario: UAT_M4-14-02-Verify the Process of Submit Portal Return-VAT
#    Given Open portal URL
#    Then Login to portal
#      | payereturns | Codei@maseno2020 |
#    And Click on my tax
#    Then Click submit returns
#    Then Select taxtype "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/div[1]/table/tbody/tr[4]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
#    Then Select period "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
#    Then Click continue "btnContinue"
#    Then Fill in fields under VAT Returns with the double "30"
#    Then Click next button "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[3]/div/button[2]"
#    Then Click next button "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[3]/div/button[2]"
#    Then fill in VAT return fields with value "30"
#    Then Click next button "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[3]/div/button[2]"
#    Then Confirm checkbox "//*[@id='id_vatReturnSummaryForm']/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"
#    And Click Submit: xpath "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[3]/div/button[3]"
#    Then Verify success message "Your VAT Returns request has been submitted successfully"

   @UAT_M4-14-01 @UAT_M4-14-02
  Scenario: UAT_M4-14-02-Verify the Process of Submit Portal Return-VAT | Verify the fields in Submit Portal Return screens
    Given Open portal URL
    Then Login to portal
      | maxipain | Codei@maseno2020 |
    And Click on my tax
    Then Click submit returns
    Then Select taxtype "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/div[1]/table/tbody/tr[3]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Select period "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Click continue "btnContinue"
    Then Set returns to nill
    Then Click next button "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[3]/div/button[2]"
    Then Confirm and certify information
    And Click Submit: xpath "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[3]/div/button[3]"
    Then Verify success message "Your VAT Returns request has been submitted successfully"

   @UAT_M4-14-03
  Scenario: UAT_M4-14-03-Verify the Process of Submit Portal Return-PAYE
    Given Open portal URL
    Then Login to portal
      | payereturns | Codei@maseno2020 |
    And Click on my tax
    Then Click submit returns
    Then Select taxtype "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/div[1]/table/tbody/tr[3]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Select period "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Click continue "btnContinue"
    Then Select nill return "//*[@id='id_payeReturnForm']/div[2]/div/tb-checkbox/div/div[2]/p-checkbox"
    Then Confirm checkbox "//*[@id='id_payeReturnForm']/div[4]/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"
    And Click Submit: xpath "//*[@id='btnSave']"
    Then Verify success message "Your PAYE Returns request has been submitted successfully"

   @UAT_M4-14-04
  Scenario: UAT_M4-14-04-Verify the Process of Save Portal Return
    Given Open portal URL
    Then Login to portal
      | maxipain | Codei@maseno2020 |
    And Click on my tax
    Then Click submit returns
    #VAT
    Then Select taxtype "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/div[1]/table/tbody/tr[3]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Select period "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Click continue "btnContinue"
    Then Select nill return "//*[@id='id_outputVATForm']/div[1]/div/tb-checkbox/div/div[2]/p-checkbox"
    And Click Save as draft: xpath "//*[@id='id_vatForm']/form-wizard/div/div/div[5]/div/div[2]/button"

   @UAT_M4-14-05
  Scenario: UAT_M4-14-05-Verify the Process of Return Validation Fails
    Given Open portal URL
    Then Login to portal
      | payereturns | Codei@maseno2020 |
    And Click on my tax
    Then Click submit returns
    #PAYE
    Then Select taxtype "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/p-table/div/div[1]/table/tbody/tr[3]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Select period "/html/body/trips-app/div/returns/div/return-select/div/div/div[1]/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/div/p-tableradiobutton/div/div[2]/span"
    Then Click continue "btnContinue"
    Then Select nill return "//*[@id='id_payeReturnForm']/div[2]/div/tb-checkbox/div/div[2]/p-checkbox"
    And Verify save button is disabled "btnSave"

