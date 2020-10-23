Feature: [SUC:09-09] Print Return Reports

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click reporting > reports

  @SUC:09-09 @UAT_M4-09-01 @BR01
  Scenario: UAT_M4-09-01 Verify the Process of Print Reports- Amended Returns Report
    Then Select report to print "Amended Returns Report"
    Then Enter start date as "01/08/2020"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Amended Returns Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-02 @BR01
  Scenario: UAT_M4-09-02 Verify the Process of Print Reports-Fact of Filing Report
    Then Select report to print "Fact Of Filing Report"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Fact Of Filing Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-03 @BR01
  Scenario: UAT_M4-09-03 Verify the Process of Print Reports-Return Activity Report
    Then Select report to print "Return Activity Report"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Return Activity Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-04 @BR01
  Scenario: UAT_M4-09-04 Verify the Process of Print Reports-Error Returns
    Then Select report to print "Error Returns"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Error Returns.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-05 @BR01
  Scenario: UAT_M4-09-05 Verify the Process of Print Reports-Returns Keyed/Filed Report
    Then Select report to print "Returns Keyed/Filed Report"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Returns Keyed_Filed Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-06 @BR01
  Scenario: UAT_M4-09-06 Verify the Process of Print Reports-Returns Outcome Report
    Then Select report to print "Returns Outcome Report"
    Then Select tax office "Balaka"
    Then Select outcome status
    Then Select business sector
    Then Select taxpayer type
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Returns Outcome Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

#  @SUC:09-09 @UAT_M4-09-07
#  Scenario Outline: UAT_M4-09-07 Verify the Process of Print Reports-Filed Returns in Loss Report
#    Given User is in browser to launch application url
#    Then Enter  username "<username>" and  password "<password>" to login
#    Then Navigate to  Reporting---->Reports
#    And click Filed Returns in Loss Report link
#    Then Select a report Format As "<pdf>"
#    And Select the start Date and  end Date provided
#    Then Click the Taxpayer Category "<taxpayer_category>"
#    Then Click to select  Tax Office "<tax_office>"
#    Then click to select a Return Type "<return_type>"
#    And Click to select a  Business Sector "<business_sector>"
#    And Click to run the Report
#    Examples:
#      | pdf | taxpayer_category | tax_office | username  | password | business_sector | return_type            |
#      | PDF | Individual        | Dedza      | tripsuser | Passw0rd | Cargo handling  | Domestic Excise Return |

  @SUC:09-09 @UAT_M4-09-08 @BR01
  Scenario: UAT_M4-09-08 Verify the Process of Print Reports-Returns By Method Of Submission
    Then Select report to print "Returns By Method Of Submission"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select submission method
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Returns By Method Of Submission.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"


  @SUC:09-09 @UAT_M4-09-09 @BR01
  Scenario: UAT_M4-09-09 Verify the Process of Print Reports-Returns Under Filing Extension
    Then Select report to print "Returns Under Filing Extension"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Returns Under Filing Extension.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-10 @BR01
  Scenario: UAT_M4-09-10 Verify the Process of Print Reports-Returns Keyed Awaiting Approval
    Then Select report to print "Returns Keyed/Filed Report"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Returns Keyed_Filed Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-11
  Scenario: UAT_M4-09-11 Verify the Process of Print Reports-Employee Contribution
    Then Select report to print "EmployeeContribution"
    Then Enter employer tin "%"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "EmployeeContribution.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-12
  Scenario: UAT_M4-09-12 Verify the Process of Print Reports-VAT Excess Return
    Then Select report to print "Vat Excess Return"
    Then Enter tin nimber "%"
    Then Select tax office "Balaka"
    Then Select business sector
    Then Enter start date as "01/08/2020"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Vat Excess Return.pdf" has been downloaded in downloads directory "C:\\Users\\Maxwell Maragia\\Downloads"

  @SUC:09-09 @UAT_M4-09-13
  Scenario: UAT_M4-09-13 Verify the Process of Validation Error
    Then Select report to print "Amended Returns Report"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify error message "Start Date: Validation Error: Value is required"

  @SUC:09-09 @UAT_M4-09-14 @BR01
  Scenario: UAT_M4-09-14 Verify the Process of Abandon Report
    Then Select report to print "Fact Of Filing Report"
    Then Select tax office "Balaka"
    Then Select return type
    Then Select business sector
    Then Select taxpayer category
    Then Enter start date as "01/08/2020"
    Then Click Cancel "frmReportDetails:btnCancel"
    Then Verify switch to page with url "https://backoffice.mra.mw:8443/trips-ui/faces/reports/ReportTree.xhtml"

