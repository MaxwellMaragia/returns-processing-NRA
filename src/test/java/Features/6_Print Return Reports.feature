Feature: [SUC:09-09] Print Return Reports

  Background:
    Given Open trips URL
    Then Login as Revenue Officer
      | tripsuser | Passw0rd |
    And Click reporting > reports

  @SUC:09-09 @UAT_M4-09-01 @BR01 @sanity
  Scenario: UAT_M4-09-01 Verify the Process of Print Reports- Error Returns
    Then Select report to print "Error Returns"
    Then Enter start date as "01/08/2020"
    Then Enter end date as today
    Then Select tax office "Balaka"
    Then Select return type
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Error Returns.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @SUC:09-09 @UAT_M4-09-02 @BR01 @Sanity
  Scenario: UAT_M4-09-02 Verify the Process of Print Reports-Return Activity Report
    Then Select report to print "Return Activity Report"
    Then Enter start date as "01/08/2020"
    Then Enter end date as today
    Then Select tax office "Balaka"
    Then Select return type
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Return Activity Report.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @SUC:09-09 @UAT_M4-09-03 @BR01
  Scenario: UAT_M4-09-03 Verify the Process of Print Reports-Returns Lodged And Not Keyed
    Then Select report to print "Returns Lodged And Not Keyed"
    Then Enter start date as "01/08/2020"
    Then Enter end date as today
    Then Select tax office "Balaka"
    Then Select return type two
    Then Click run report "frmReportDetails:RunReport"
    Then Verify file "Returns Lodged And Not Keyed.pdf" has been downloaded in downloads directory "C:\\Users\\Maxipain\\Downloads"

  @SUC:09-09 @UAT_M4-09-13
  Scenario: UAT_M4-09-13 Verify the Process of Validation Error
    Then Select report to print "Returns Lodged And Not Keyed"
    Then Click run report "frmReportDetails:RunReport"
    Then Verify error message "Start Date: Validation Error: Value is required"

  @SUC:09-09 @UAT_M4-09-14 @BR01
  Scenario: UAT_M4-09-14 Verify the Process of Abandon Report
    Then Select report to print "Returns Lodged And Not Keyed"
    Then Enter start date as "01/08/2020"
    Then Enter end date as today
    Then Select tax office "Balaka"
    Then Select return type two
    Then Click Cancel "frmReportDetails:btnCancel"
    Then Verify switch to page with url "http://34.241.245.79:8080/trips-ui/faces/reports/ReportTree.xhtml"

