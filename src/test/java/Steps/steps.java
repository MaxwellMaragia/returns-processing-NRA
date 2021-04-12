package Steps;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.sun.net.httpserver.Authenticator;
import io.cucumber.java.After;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class steps extends BaseClass {

    public static String ReferenceNumber="PAYER/000001386/2020";

    @Before(order = 0)
    public void method1() throws Exception {
        // this.S=S;
        Pro = new Properties();
        FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\Objects\\object.properties");
        Pro.load(fip);
    }

    @Given("^Open trips URL$")
    public void loadTripsLink() throws Throwable {
        driver.get(Pro.getProperty("MRA_BackOffice_URL"));
        driver.manage().window().maximize();
    }

    @Given("^Open portal URL$")
    public void loadPortalLink() throws Throwable {

        driver.get(Pro.getProperty("PORTAL_URL"));
        driver.manage().window().maximize();
    }

    @Then("^Login as Revenue Officer$")
    public void login_as_revenue_officer(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("BackOffice_UserName_ID")))).clear();
        driver.findElement(By.id(Pro.getProperty("BackOffice_UserName_ID"))).sendKeys(obj.get(0).get(0));
        driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).clear();
        driver.findElement(By.id(Pro.getProperty("BackOffice_Password_ID"))).sendKeys(obj.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("BackOffice_Login_ID"))).click();
    }

    //login to taxpayer portal
    @Then("^Login to portal$")
    public void portalLogin(DataTable data) throws Throwable {
        List<List<String>> obj = data.asLists();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("USERNAME_ID")))).sendKeys(obj.get(0).get(0));
        driver.findElement(By.id(Pro.getProperty("PASSWORD_ID"))).sendKeys(obj.get(0).get(1));
        driver.findElement(By.id(Pro.getProperty("LOGIN_ID"))).click();
    }

    @And("^Click returns filing and processing > adjust return$")
    public void OpenAdjustReturnsLink() {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Return Filing and Processing']"))).click();
        driver.findElement(By.xpath("//a[span='Adjust Return']")).click();
    }

    @And("^Click on return filing and processing > File return$")
    public void OpenFileReturnsLink() {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Return Filing and Processing']"))).click();
        driver.findElement(By.xpath("//a[span='File Return']")).click();
    }

    @And("^Click returns filing and processing > cancel return$")
    public void OpenCancelReturnsLink() {

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Return Filing and Processing']"))).click();
        driver.findElement(By.xpath("//a[span='Cancel Return demo']")).click();
    }

    @And("^Click on return filing and processing > Lodge return$")
    public void OpenLodgeReturnsLink() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Return Filing and Processing']"))).click();
        driver.findElement(By.xpath("//a[span='Lodge Return']")).click();
    }

    @And("^Click returns filing and processing > paye credit$")
    public void OpenPayeCreditsLink() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]"))).click();
        driver.findElement(By.xpath("//*[@id=\"MenuForm:j_idt29\"]/ul/li[11]/ul/li[6]/a")).click();
    }

    @And("^Click reporting > reports$")
    public void OpenReportsLink() {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='REPORTING']"))).click();
        driver.findElement(By.xpath("//a[span='REPORTS']")).click();
    }

    //Verify precense of elements using id
    @Then("^Verify fields dropdown \"([^\"]*)\" and next button \"([^\"]*)\" and cancel button \"([^\"]*)\"$")
    public void verifyFieldsInReturnDocumentsSelection(String Dropdown, String NextButton, String CancelButton) {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {Dropdown, NextButton, CancelButton};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.assertFalse("Field with ID '" + input_field + "' not found", false);
            }
        }
    }

    @Then("^Select return document as \"([^\"]*)\"$")
    public void select_return_document(String returnDocument) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FormSelection:returnType\"]/div[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + returnDocument + "')]")).click();
    }

    @Then("^Click next \"([^\"]*)\"$")
    public void clickNext(String NextButtonId) {
        driver.findElement(By.id(NextButtonId)).click();
    }

    @Then("^Verify presence of fields tin \"([^\"]*)\" and period number \"([^\"]*)\" and period year \"([^\"]*)\" and search button \"([^\"]*)\" and cancel button \"([^\"]*)\" and continue button \"([^\"]*)\" in find return page$")
    public void verify_presence_of_fields_in_find_returns_page(String Tin, String periodNumber, String periodYear, String search, String cancel, String Continue) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:periodnumber"))).isDisplayed();
        String Input_fields[] = {periodNumber, Tin, periodYear, Continue, search, cancel};
        for (int i = 0; i < Input_fields.length; i++) {
            WebElement form_element = driver.findElement(By.id(Input_fields[i]));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
    }

    @Then("^Verify table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_table_columns_in_find_returns_page(String tin, String taxPayerName, String returnType, String period, String documentReference, String submissionDate) throws Throwable {
        String columns[] = {tin, taxPayerName, returnType, period, documentReference, submissionDate};
        BaseClass.waitForPageToLoad();
        //check if columns exist
        for (String column : columns) {
            String TableRowXpath = "//span[contains(text(),'" + column + "')]";
            WebElement Table_Column = driver.findElement(By.xpath(TableRowXpath));
            if (Table_Column.isDisplayed()) {
                System.out.println("Column : " + column + " found ");
                Assert.assertTrue(true);
                Thread.sleep(2000);
            } else {
                Assert.assertFalse("Column : " + column + " not found", false);
                System.out.println("Column : " + column + " not found ");
            }
        }
    }

    @Then("^Enter tin as \"([^\"]*)\" and period number as \"([^\"]*)\" and year as \"([^\"]*)\"$")
    public void enter_tin_and_period_number_and_year(String Tin, String Number, String Year) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:tin"))).sendKeys(Tin);
        driver.findElement(By.id("SearchForm:periodnumber")).sendKeys(Number);
        driver.findElement(By.id("SearchForm:periodyear")).sendKeys(Year);
    }

    @Then("^Click search$")
    public void click_search() {

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:j_idt40"))).click();
    }

    @Then("^Click table column \"([^\"]*)\"$")
    public void click_table_column(String ColumnXpath) throws Throwable {
        Thread.sleep(8000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ColumnXpath))).click();
//        Actions action = new Actions(driver);
//        action.doubleClick(driver.findElement(By.xpath(ColumnXpath))).perform();
    }

    @Then("^Click continue \"([^\"]*)\"$")
    public void click_continue(String continueID) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id(continueID)).click();
    }

    //verify if fields are present and readonly
    @Then("^Verify readonly fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_readonly_fields(String tinField, String taxPayerNameField, String tradingNameField, String postalAddressField, String emailAddressField, String mobileNumberField, String periodFromField, String periodToField) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:tin"))).isDisplayed();
        String[] Input_fields = {tinField, taxPayerNameField, tradingNameField, postalAddressField, emailAddressField, mobileNumberField, periodFromField, periodToField};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);
                if (form_element.isEnabled()) {
                    Assert.assertFalse("'" + input_field + "' is not read only", false);
                }
            } else {
                Assert.assertFalse("'" + input_field + "' is not displayed", false);
            }
        }
    }

    @Then("^Verify existence of buttons \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in paye tax return$")
    public void verify_existence_of_buttons_in_paye_tax_return(String add, String update, String view, String remove, String upload, String downloadTemplate) throws Throwable {
        BaseClass.waitForPageToLoad();
        String[] Input_fields = {add, update, view, remove, upload, downloadTemplate};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Button '" + input_field + "' not found");
            }
        }
    }

    @Then("^Verify error message \"([^\"]*)\"$")
    public void verify_error_message(String error) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + error + "')]")));
        if (errorMessage.isDisplayed()) {
            System.out.println("Error message ('" + error + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Then("^Verify no data is found in table$")
    public void verify_no_data_is_found_in_table() throws Throwable {
        WebElement noDataXpath = driver.findElement(By.xpath("//td[contains(text(),'No records found.')]"));
        if (noDataXpath.isDisplayed()) {
            Assert.assertTrue("No data found in table", true);
        } else {

            Assert.assertFalse("Data found in table", false);
        }
    }

    //--------------------Portal-----------------------------------------------------------------------------------------------------------------//

    @Then("^Click on my tax$")
    public void click_my_tax_button() throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("My_Tax_Button_ID")))).click();
    }

    @Then("^Click submit returns$")
    public void click_submit_returns() throws Throwable {
        Thread.sleep(7000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("SubmitReturn_ID")))).click();
    }

    @Then("^Select taxtype \"([^\"]*)\"$")
    public void selectTaxType(String taxTypeXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(taxTypeXpath))).click();
    }

    @Then("^Select period \"([^\"]*)\"$")
    public void selectPeriod(String periodXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(periodXpath))).click();
    }

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in vat return page$")
    public void verify_input_fields_in_vat_return_page(String period, String valueOfSales, String vatOnImportedServices, String vatOnDisposalOfCapitalItems, String valueOfExportSupplies, String valueOfZeroRatedSupplies, String valueOfExemptSupplies, String totalVat, String cancel) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_period"))).isDisplayed();
        String Input_fields[] = {period, valueOfSales, vatOnImportedServices, vatOnDisposalOfCapitalItems, valueOfExemptSupplies, valueOfExportSupplies, valueOfZeroRatedSupplies, totalVat, cancel};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue("Element with id '" + input_field + "' has been found", true);
            } else {
                Assert.fail();
            }
        }
    }

    @Then("^Fill in fields under VAT Returns with the double \"([^\"]*)\"$")
    public void fill_in_fields_under_vat_returns_with_the_double(String Amount) throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement valueOneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_value1")));
        if (valueOneField.isEnabled()) {
            valueOneField.sendKeys(Amount);
            driver.findElement(By.id("id_vat2")).sendKeys(Amount);
            driver.findElement(By.id("id_vat3")).sendKeys(Amount);
            driver.findElement(By.id("id_value4")).sendKeys(Amount);
            driver.findElement(By.id("id_value5")).sendKeys(Amount);
            driver.findElement(By.id("id_value6")).sendKeys(Amount);
        }
    }

    @Then("^Set returns to nill$")
    public void set_returns_to_nill() throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement valueOneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_value1")));
        if (valueOneField.isEnabled()) {
            driver.findElement(By.xpath("//*[@id=\"id_outputVATForm\"]/div[1]/div/tb-checkbox/div/div[2]/p-checkbox/div/div[2]")).click();
        }
    }

    @Then("^Confirm and certify information$")
    public void confirm_and_certify_information() throws Throwable {
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id_vatReturnSummaryForm\"]/tb-checkbox/div/div[2]/p-checkbox/div/div[2]"))).click();
    }

    @Then("^Click next button \"([^\"]*)\"$")
    public void click_next_button(String nextXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextXpath))).click();
    }

    @Then("^Verify download and upload buttons with id \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_download_and_upload_buttons_with_id(String download, String upload) throws Throwable {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {download, upload};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue("Button with id '" + input_field + "' has been found", true);

            } else {
                Assert.assertFalse("Button with id '" + input_field + "' has not been found", false);
            }
        }
    }

    @Then("^Click Cancel \"([^\"]*)\"$")
    public void click_cancel(String cancelID) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cancelID))).click();
    }

    @Then("^Click ok in confirm dialogue$")
    public void click_ok_in_confirm_dialogue() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/trips-app/p-confirmdialog/div/div[3]/button[1]"))).click();
        Thread.sleep(3000);
    }

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in paye$")
    public void verify_input_fields_in_paye(String period, String nillCheckbox, String totalGrossIncome, String totalPaye, String printButton, String canceButton, String submitButton) {
        BaseClass.waitForPageToLoad();
        String Input_fields[] = {period, nillCheckbox, totalGrossIncome, totalPaye, printButton, canceButton, submitButton};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue("Element with id '" + input_field + "' has been found", true);

            } else {
                Assert.fail();
            }
        }
    }

    @Then("^fill in VAT return fields with value \"([^\"]*)\"$")
    public void fill_in_vat_return_fields_with_value(String amount) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_value8"))).sendKeys(amount);
        driver.findElement(By.id("id_value9")).sendKeys(amount);
        driver.findElement(By.id("id_value10")).sendKeys(amount);
        driver.findElement(By.id("id_vat11")).sendKeys(amount);
        driver.findElement(By.id("id_vat13")).sendKeys(amount);
        driver.findElement(By.id("id_value14")).sendKeys(amount);
        driver.findElement(By.id("id_value15")).sendKeys(amount);
        driver.findElement(By.id("id_value18")).sendKeys(amount);
        driver.findElement(By.id("id_vat18")).sendKeys(amount);
    }

    @Then("^Select nill return \"([^\"]*)\"$")
    public void select_nill_return(String nillXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nillXpath))).click();
    }

    @Then("^Confirm checkbox \"([^\"]*)\"$")
    public void confirm_checkbox(String checkboxXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkboxXpath))).click();
    }

    @And("^Click Submit: xpath \"([^\"]*)\"$")
    public void click_submit_xpath(String submitXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(submitXpath))).click();
    }

    @And("^Click Save as draft: xpath \"([^\"]*)\"$")
    public void click_save_as_draft_xpath(String draftXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(draftXpath))).click();
    }

    @Then("^Click Save with errors$")
    public void saveWithErrors() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:SAVE_WITH_ERROR"))).click();
    }

    @Then("^Modify PAYE calculations if not nil$")
    public void modifyTaxCalculations() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 60);
        String totalReturns = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:TotalPaye_input"))).getText();

        if (totalReturns != "0.00") {
            //change to nill
            driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:nillReturn\"]/div[2]/span")).click();
        }
    }

    @Then("^Verify save with errors button is displayed$")
    public void verifySaveWithErrorsButton() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement saveWithErrorsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:SAVE_WITH_ERROR")));

        if (saveWithErrorsButton.isDisplayed()) {
            System.out.println("Save with errors button displayed");
        } else {
            Assert.assertFalse("Save with errors button not visible", false);
        }
    }

    @Then("^Verify success message \"([^\"]*)\"$")
    public void verify_status_change(String Status) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + Status + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Status + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @And("^Verify save button is disabled \"([^\"]*)\"$")
    public void verify_save_button_is_disabled(String saveButtonID) throws Throwable {
        Thread.sleep(2000);
        WebElement saveButton = driver.findElement(By.id(saveButtonID));
        if (saveButton.isEnabled()) {
            Assert.fail();
        } else {
            Assert.assertTrue("Save button is disabled", true);
        }
    }

    //------------------------------------------------Process tax return------------------------------------------------//

    @Then("^Fill in declaration fields name as \"([^\"]*)\", designation as \"([^\"]*)\", declaration date as \"([^\"]*)\" for taxtype \"([^\"]*)\"$")
    public void fill_in_declaration_fields(String name, String designation, String date, String returnType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);

        String declarantNameLocator = "";
        String declarantPositionLocator = "";
        String declarationDateLocator = "";
        String amendmentReason = "";

        if(returnType.equals("Capital Gains Tax Return")){
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:CITDetailsTab:reasonForAdjustment\"]/div[3]";
        }
        if(returnType.equals("CIT Return (Final)")){
            declarantNameLocator = "FlexibleFormEntity:CITDetailsTab:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:CITDetailsTab:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:CITDetailsTab:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:CITDetailsTab:reasonForAdjustment\"]/div[3]";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Attachments')]"))).click();
        }
        if(returnType.equals("CIT Return (Provisional)")){
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";

        }
        if (returnType.equals("PAYE Returns")) {

            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]";
            String totalReturns = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:TotalPaye_input"))).getText();

            if (totalReturns != "0.00") {
                //change to nill
                driver.findElement(By.xpath("//*[@id=\"FlexibleFormEntity:nillReturn\"]/div[2]/span")).click();
            }

        }

        if (returnType.equals("Excise Tax Return")) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:nilReturn\"]/div[2]/span"))).click();
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]";
        }

        if (returnType.equals("FTT Return")) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:NilReturn\"]/div[2]/span"))).click();
            declarantNameLocator = "FlexibleFormEntity:DeclarantName";
            declarantPositionLocator = "FlexibleFormEntity:DeclarantPosition";
            declarationDateLocator = "FlexibleFormEntity:DeclarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:ReasonForAdjustment\"]/div[3]";
        }

        if (returnType.equals("GST Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]";
        }

        if (returnType.equals("PIT Return (Final)")) {
            declarantNameLocator = "FlexibleFormEntity:pitShowTab:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:pitShowTab:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:pitShowTab:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:pitShowTab:reasonForAdjustment\"]/div[3]";

        }

        if (returnType.equals("PIT Return (Provisional)")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]";
        }

        if (returnType.equals("Payroll Tax Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]";
        }

        if (returnType.equals("Rental income Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]";
            //
            // C:\id_doc.pngwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:NilReturn\"]/div[2]/span"))).click();
        }

        if (returnType.equals("WHT (10.5% and 5.5%) Return")) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:nilReturn\"]/div[2]/span"))).click();
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            amendmentReason = "//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]";
        }

        if (returnType.equals("CGT Return")){
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }


        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(amendmentReason))).click();

        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'INCREASE VAT PENALTIES - DEBIT')]")).click();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).sendKeys(name);
        Thread.sleep(2000);
        driver.findElement(By.id(declarantPositionLocator)).clear();
        driver.findElement(By.id(declarantPositionLocator)).sendKeys("Software developer");
        Thread.sleep(2000);
        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('" + declarationDateLocator + "').setAttribute('value', '01/01/2018')");
        Thread.sleep(2000);

    }

    @Then("^Fill declaration name field with error$")
    public void fill_in_declaration_fields_with_error_name() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);

        String declarantNameLocator = "FlexibleFormEntity:declarantName";
        String declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
        String declarationDateLocator = "FlexibleFormEntity:declarationDate_input";


        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).sendKeys("DR MARGIE WAMBUI&*&^^^^^");
        Thread.sleep(2000);
        driver.findElement(By.id(declarantPositionLocator)).clear();
        driver.findElement(By.id(declarantPositionLocator)).sendKeys("Software developer");
        Thread.sleep(2000);
        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('" + declarationDateLocator + "').setAttribute('value', '01/01/2018')");
        Thread.sleep(2000);

    }

    @Then("^Fill in declaration fields \"([^\"]*)\"$")
    public void fill_in_declaration_fields(String returnType) throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,50);
        String declarantNameLocator = "";
        String declarantPositionLocator = "";
        String declarationDateLocator = "";

        if(returnType.equals("Capital Gains Tax Return")){
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }
        if(returnType.equals("CIT Return (Final)")){
            declarantNameLocator = "FlexibleFormEntity:CITDetailsTab:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:CITDetailsTab:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:CITDetailsTab:declarationDate_input";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:CITDetailsTab:BusinessLocation\"]/div[3]"))).click();
            Thread.sleep(1000);
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Balance Sheet')]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:CITDetailsTab:balanceSheetDate_input"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:CITDetailsTab:balanceSheetDate_input"))).sendKeys(Keys.ENTER);


            Thread.sleep(1000);
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:currentAssetsLocalCurrencies_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:currentAssetsCedis_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:stocksInventories_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:accountReceivables_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:fixedAssets_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:otherAssets_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:tradePayablesLocalCurrencies_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:tradePayablesLeones_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:otherPayablesLocalCurrencies_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:otherPayablesLeones_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:shareholdersFund_input")).sendKeys("0");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Income Statement')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:localSales_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:exportSales_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:rentIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:dividends_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:interest_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:naturalResourcePayment_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:royalty_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:annuity_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:otherSpecify_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:lessCostOfSalesAndExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:generalExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:depreciation_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:foreignExchangeLosses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:otherCostsExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:unrealisedExchangeLoss_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:depreciation_ab_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:gpForBadDebt_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:serviceBenefit_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:disallowedInterest_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:capitalisedRepairs_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:entertainmentExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:businessAsset_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:othersSpecify_ab_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:interestIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:saleBusinessAsset_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:nt_rentalIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:nt_othersSpecify_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:ad_exchangeGain_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:ad_capitalAllowance_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:ad_endOfServicePaid_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:ad_saleOfBusinessAsset_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:ad_investmentAllowance_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:ad_othersSpecify_input")).sendKeys("0");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Computation Of Taxes')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:lossBroughtFromPrevYear_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:lossRelief_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:wht_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:taxesPaidDirectly_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:CITDetailsTab:priorPeriodCredit_input")).sendKeys("0");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Attachments')]")).click();
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:CITDetailsTab:attachmentTable:j_id1"))).click();
            WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
            driver.switchTo().frame(frame);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexbleFormAttachment:DocType\"]/div[3]"))).click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Annual Financial Statements')]"))).click();
            Thread.sleep(2000);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            driver.findElement(By.id("FlexbleFormAttachment:id_reference")).sendKeys(String.valueOf(timestamp.getTime()));
            Thread.sleep(1000);
            driver.findElement(By.id("FlexbleFormAttachment:id_attachment_input")).sendKeys("C:\\id_doc.png");
            Thread.sleep(3000);
            driver.findElement(By.id("FlexbleFormAttachment:Ok")).click();
            driver.switchTo().defaultContent();



        }
        if(returnType.equals("CIT Return (Provisional)")){
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:chargeableIncome_input"))).sendKeys("80000");
            Thread.sleep(2000);
            driver.findElement(By.id("FlexibleFormEntity:quarterlyPayment_input")).sendKeys("10000");

        }
        if (returnType.equals("PAYE Returns")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:nillReturn\"]/div[2]/span"))).click();
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";

        }

        if (returnType.equals("Excise Tax Return")) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:nilReturn\"]/div[2]/span"))).click();
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }

        if (returnType.equals("FTT Return")) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:NilReturn\"]/div[2]/span"))).click();
            declarantNameLocator = "FlexibleFormEntity:DeclarantName";
            declarantPositionLocator = "FlexibleFormEntity:DeclarantPosition";
            declarationDateLocator = "FlexibleFormEntity:DeclarationDate_input";
        }

        if (returnType.equals("GST Return")) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:nilReturn\"]/div[2]/span"))).click();
            driver.findElement(By.id("FlexibleFormEntity:attachmentTable:j_id1")).click();
            WebElement Iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
            driver.switchTo().frame(Iframe);
            driver.findElement(By.xpath("//*[@id=\"FlexbleFormAttachment:DocType\"]/div[3]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexbleFormAttachment:DocType_1"))).click();
            Thread.sleep(2000);
            driver.findElement(By.id("FlexbleFormAttachment:id_reference")).sendKeys("Attachment");
            driver.findElement(By.id("FlexbleFormAttachment:id_attachment_input")).sendKeys("C:\\id_doc.png");
            driver.findElement(By.id("FlexbleFormAttachment:Ok")).click();
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }

        if (returnType.equals("PIT Return (Final)")) {
            declarantNameLocator = "FlexibleFormEntity:pitShowTab:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:pitShowTab:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:pitShowTab:declarationDate_input";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'BALANCE SHEET')]"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:pitShowTab:balanceSheetDate_input"))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:pitShowTab:balanceSheetDate_input"))).sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:stocksInventories_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:receivables_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:bankBalances_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:otherCurrentAssets_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:prePayment_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:building_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:furnitureAndEquipments_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:motorVehicles_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:otherAssets_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:billsPayable_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:loans_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:accruals_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:otherPayables_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:capital_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:netProfitBalanceSheet_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:drawings_input")).sendKeys("0");

            driver.findElement(By.xpath("//a[contains(text(),'INCOME STATEMENT')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:basicSalary_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:cashAllowance_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:otherCashBenefit_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:bonus_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:accomodation_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:vehicle_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:bkOthers_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:otherEmploymentIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:rentIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:dividends_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:interest_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:naturalResourcePayment_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:royalty_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:directorsFees_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:commission_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:charges_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:discounts_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:premium_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:annuity_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:otherInvestmentIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:costOfSales_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:operatingExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:generalExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:depreciation_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:foreignExchangeLoss_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:otherCostsExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:unrealisedExchangeLoss_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackDepreciation_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackBadDebt_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackServiceBenifit_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackDisallowedInterest_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackCapitalisedRepairs_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackEntertainmentExpenses_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackSaleBusinessAsset_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:addBackOther_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:nonTaxInterestIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:nonTaxSaleBusinessAsset_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:nonTaxRentalIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:nonTaxOthers_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:adExchangeGain_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:adCapitalAllowance_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:adEndOfServicePaid_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:adSaleOfBusinessAsset_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:adInvestmentAllowance_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:adOthers_input")).sendKeys("0");

            driver.findElement(By.xpath("//a[contains(text(),'TAX COMPUTATION')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:finalTotalEmploymentIncome_input")).sendKeys("90000");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:lossBroughtFromPreviousYear_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:lossReliefForTheYear_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:tpWHT_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:tpTaxesPaidDirectly_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:tpPAYETaxPaid_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:pitShowTab:tpPriorPeriodCredit_input")).sendKeys("0");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'ATTACHMENTS')]")).click();
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:pitShowTab:attachmentTable:j_id1"))).click();
            WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
            driver.switchTo().frame(frame);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexbleFormAttachment:DocType\"]/div[3]"))).click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Annual Financial Statements')]"))).click();
            Thread.sleep(2000);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            driver.findElement(By.id("FlexbleFormAttachment:id_reference")).sendKeys(String.valueOf(timestamp.getTime()));
            Thread.sleep(1000);
            driver.findElement(By.id("FlexbleFormAttachment:id_attachment_input")).sendKeys("C:\\id_doc.png");
            Thread.sleep(3000);
            driver.findElement(By.id("FlexbleFormAttachment:Ok")).click();
            driver.switchTo().defaultContent();

            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'PERSONAL DETAILS')]")).click();


        }

        if (returnType.equals("PIT Return (Provisional)")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:businessIncome_input"))).sendKeys("90000");
            driver.findElement(By.id("FlexibleFormEntity:employmentIncome_input")).sendKeys("50000");
            driver.findElement(By.id("FlexibleFormEntity:otherIncome_input")).sendKeys("50000");
            driver.findElement(By.id("FlexibleFormEntity:totalChargableIncome_input")).sendKeys("0");
            driver.findElement(By.id("FlexibleFormEntity:quarterlyTaxPayable_input")).sendKeys("0");
        }

        if (returnType.equals("Payroll Tax Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }

        if (returnType.equals("Rental income Return")) {
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:NilReturn\"]/div[2]/span"))).click();
        }

        if (returnType.equals("WHT (10.5% and 5.5%) Return")) {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:nilReturn\"]/div[2]/span"))).click();
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantDesignation";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }

        if (returnType.equals("CGT Return")){
            declarantNameLocator = "FlexibleFormEntity:declarantName";
            declarantPositionLocator = "FlexibleFormEntity:declarantPosition";
            declarationDateLocator = "FlexibleFormEntity:declarationDate_input";
        }


        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(declarantNameLocator))).sendKeys("DR MARGIE WAMBUI");
        Thread.sleep(2000);
        driver.findElement(By.id(declarantPositionLocator)).clear();
        driver.findElement(By.id(declarantPositionLocator)).sendKeys("Software developer");
        Thread.sleep(2000);
        //lets use javascript to send date fields since sendKeys method has inconsistent behavior in date fields
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('" + declarationDateLocator + "').setAttribute('value', '01/01/2018')");
        Thread.sleep(2000);

    }

    @Then("^Verify save success message \"([^\"]*)\"$")
    public void verify_success_message(String Message) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + Message + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + Message + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    //------------------------------------------------Lodge paper return---------------------------------------------------------//

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in lodge return page$")
    public void verify_input_fields_in_lodge_return_page(String tin, String taxpayername, String tradingname, String returndocument, String period, String lodgementdate, String savebutton, String cancelbutton) throws Throwable {
        BaseClass.waitForPageToLoad();
        String[] Input_fields = {tin, taxpayername, tradingname, returndocument, period, lodgementdate, savebutton, cancelbutton};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail("Element with ID '" + input_field + "' not found");
            }
        }
    }

    @Then("^Click Return document search button$")
    public void click_return_document_search_button() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ReturnsLodgement:searchId"))).click();
    }

    @Then("^Switch to frame$")
    public void shift_focus_to_frame() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 300);
        WebElement Iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(Iframe);
    }

    @Then("^Verify input fields \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in document search frame$")
    public void verify_input_fields_in_document_search_frame(String tindocumentsearch, String periodnumber, String periodyear) throws Throwable {
        Thread.sleep(2000);
        String[] Input_fields = {tindocumentsearch, periodnumber, periodyear};

        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail("Element with ID '" + input_field + "' not found");
            }
        }
    }

    @Then("^Verify searchform buttons \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
    public void verify_searchform_buttons(String search, String cancel, String Continue) {
        String[] Input_fields = {search, cancel, Continue};
        for (String input_field : Input_fields) {
            WebElement form_element = driver.findElement(By.id(input_field));
            if (form_element.isDisplayed()) {
                Assert.assertTrue(true);

            } else {
                Assert.fail("Button with ID '" + input_field + "' not found");
            }
        }
    }

    @Then("^Verify table columns \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" in search document frame$")
    public void verify_table_columns_in_search_document_frame(String tincolumn, String taxpayernamecolumn, String documenttypecolumn, String periodnumbercolumn, String periodyearcolumn) throws Throwable {
        String columns[] = {tincolumn, taxpayernamecolumn, documenttypecolumn, periodnumbercolumn, periodyearcolumn};

        //check if columns exist
        for (String column : columns) {
            String TableRowXpath = "//span[contains(text(),'" + column + "')]";
            WebElement Table_Column = driver.findElement(By.xpath(TableRowXpath));
            if (Table_Column.isDisplayed()) {

                System.out.println("Column : " + column + " found ");
                Assert.assertTrue(true);
                Thread.sleep(2000);
            } else {
                Assert.assertFalse("Column : " + column + " not found", false);
                System.out.println("Column : " + column + " not found ");
            }
        }
    }

    @Then("^Search for document with by filling Tin as \"([^\"]*)\" Return document as \"([^\"]*)\" Period number as \"([^\"]*)\" and Period year as \"([^\"]*)\"$")
    public void search_for_return_document(String tin, String returnDocument, String periodNumber, String periodYear) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"SearchForm:ReturnType_label\"]")).click();
        Thread.sleep(1000);
        // scroll to it

        WebElement returntype = driver.findElement(By.xpath("//li[contains(text(),'" + returnDocument + "')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(returntype);
        actions.click(returntype).perform();

        //driver.findElement(By.xpath("//li[contains(text(),'" + returnDocument + "')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:TIN")).sendKeys(tin);
        driver.findElement(By.id("SearchForm:PeriodNumber")).sendKeys(periodNumber);
        driver.findElement(By.id("SearchForm:PeriodYear")).sendKeys(periodYear);
    }

    @Then("^Verify nill return check box is present$")
    public void verifyNillCheckbox() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement nillCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ReturnsLodgement:id_NilReturn")));
        if(nillCheckbox.isDisplayed()){
            System.out.println("NIll checkbox displayed");
        }
        else{
            Assert.assertFalse("Nill checkbox not displayed",false);
        }
    }

    @Then("^Search for document with only Tin as \"([^\"]*)\"$")
    public void search_for_document_with_only_tin(String tin) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("SearchForm:TIN")).sendKeys(tin);
    }

    @Then("^Click search button \"([^\"]*)\"$")
    public void click_search_button(String searchID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(searchID))).click();
    }

    @Then("^Enter lodgement date as \"([^\"]*)\"$")
    public void enter_lodgement_date_as(String date) throws Throwable {
        Thread.sleep(10000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ReturnsLodgement:id_LodgementDate_input")));


        dateElement.clear();
        Thread.sleep(2000);
        dateElement.sendKeys(date);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform();
    }

    @Then("^Enter liability as \"([^\"]*)\"$")
    public void enter_liability(String liability) throws Throwable {
        Thread.sleep(5000);
        driver.findElement(By.id("ReturnsLodgement:id_Liability_input")).sendKeys(liability);
    }

    @Then("^Enter liability as \"([^\"]*)\" and date of lodgement as \"([^\"]*)\"$")
    public void enter_liability_and_date_of_lodgement(String liability, String date) throws Throwable {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ReturnsLodgement:id_Liability_input"))).clear();

        driver.findElement(By.id("ReturnsLodgement:id_Liability_input")).sendKeys(liability);

        if (date.length() > 1) {
            if(date.equalsIgnoreCase("today")){
                driver.findElement(By.id("ReturnsLodgement:id_LodgementDate_input")).clear();
                driver.findElement(By.id("ReturnsLodgement:id_LodgementDate_input")).sendKeys(todaysDate());
                Thread.sleep(2000);
            }
            else{
                driver.findElement(By.id("ReturnsLodgement:id_LodgementDate_input")).clear();
                driver.findElement(By.id("ReturnsLodgement:id_LodgementDate_input")).sendKeys(date);
                Thread.sleep(2000);
            }

        }
    }

    @Then("^Close print modal$")
    public void close_print_preview() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt16:Cancel"))).click();
    }

    @Then("^Click print preview \"([^\"]*)\"$")
    public void closePrint(String taxType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_idt16:Cancel"))).click();
    }

    //-------------------------------------------------Process Paper Form--------------------------------------------------------------//

    @Then("^Click add \"([^\"]*)\"$")
    public void click_add(String addID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(addID))).click();
    }

    @Then("^Find entity by Tin \"([^\"]*)\"$")
    public void find_entity_by_tin(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:tin"))).sendKeys(tin);
    }

    @Then("^Enter designation as \"([^\"]*)\" and basic salary as \"([^\"]*)\"$")
    public void enter_designation_and_basic_salary(String designation, String salary) throws Throwable {
        Thread.sleep(1000);
        driver.findElement(By.id("TestFlexibleForm:Designation")).sendKeys(designation);
        driver.findElement(By.id("TestFlexibleForm:BasicSalaryAndWages_input")).sendKeys(salary);
    }

    @Then("^Fill in PAYE details$")
    public void fillPayeDetails() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("TestFlexibleForm:designation"))).sendKeys("Software developer");
        driver.findElement(By.id("TestFlexibleForm:basicSalary_input")).sendKeys("250,000");
        driver.findElement(By.id("TestFlexibleForm:rentalAllowance_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:overtime_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:commission_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:pension_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:anyOtherAllowances_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:leavePassageAllowance_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:bonus_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:leaveAllowance_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:benefitInKind_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:transportAllowance_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:clothingAllowance_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:stipend_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:anyOtherIncome_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:nassitContribution_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:grossIncome_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:taxableIncome_input")).sendKeys("2000");
        driver.findElement(By.id("TestFlexibleForm:paye_input")).sendKeys("2000");
        Thread.sleep(5000);
    }

    @Then("^Switch to default$")
    public void switch_to_default() throws Throwable {
        Thread.sleep(1000);
        driver.switchTo().defaultContent();
    }

    @Then("^Switch to frame 2$")
    public void shift_focus_to_second_frame() throws Throwable {
        Thread.sleep(9000);

        driver.switchTo().frame(1);
    }

    @And("^Click ok: xpath \"([^\"]*)\"$")
    public void click_ok_xpath(String okXpath) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(okXpath))).click();
    }

    @And("^Click ok")
    public void click_ok() throws Throwable {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions actions = new Actions(driver);
        WebElement okButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='TestFlexibleForm:Save']")));
        actions.doubleClick(okButton).perform();

    }

    @Then("^Fill in Transaction details$")
    public void fillVatTransactionDetails() throws Throwable {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement totalValueSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:tdValue1_input")));
        if(totalValueSelector.isEnabled()){
            totalValueSelector.sendKeys("1000000");
        }
    }

    @Then("^Enter transaction information for local purchases: date \"([^\"]*)\", inv number \"([^\"]*)\", VAT \"([^\"]*)\"$")
    public void enter_transaction_information_for_local_purchases(String date, String invoiceNumber, String vat) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.id("TestFlexibleForm:DateOfInvoice_input")).sendKeys(date);
        driver.findElement(By.id("TestFlexibleForm:InvoiceNumber")).sendKeys(invoiceNumber);
        driver.findElement(By.id("TestFlexibleForm:VatOnSupplies_input")).sendKeys(vat);
    }

    @Then("^Verify numeric fields are readonly and total is zero$")
    public void verify_numeric_fields_are_readonly_and_total_is_zero() throws Throwable {
        Thread.sleep(2000);
        WebElement salesVat = driver.findElement(By.id("FlexibleFormEntity:tdVat17"));
        String total = driver.findElement(By.id("FlexibleFormEntity:TotalVatLocalPurch_input")).getText();
        if (salesVat.isEnabled()) {
            Assert.fail("Field is not read only");
        } else {
            if (total.equals("0.00")) {
                Assert.assertTrue("Total VAT is 0.00", true);
            } else {
                Assert.assertFalse("Total VAT is not 0.00", false);
            }
        }
    }

    //.....................................................amend tax returns.........................................................................//
    @Then("^Select reason for amendment as \"([^\"]*)\"$")
    public void

    select_reason_for_amendment(String amendmentReason) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:reasonForAmendment\"]/div[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'" + amendmentReason + "')]")).click();
    }

    @Then("^Click reset$")
    public void click_reset() throws Throwable {
        driver.findElement(By.id("FlexibleFormEntity:reset")).click();
    }

    @Then("^Verify name field has been reset \"([^\"]*)\"$")
    public void verify_name_field_has_been_reset(String name) throws Throwable {
        Thread.sleep(3000);
        String declarantName = driver.findElement(By.id("FlexibleFormEntity:declarantName")).getText();
        if (declarantName == name) {
            Assert.assertTrue("Fields reset", true);
            System.out.println("Name fields reset");
        } else {
            Assert.assertFalse("Fields not reset", false);
        }
    }

    //.......................................................cancel return.............................................................................//
    @Then("^Select reason for cancellation as \"([^\"]*)\" \"([^\"]*)\"$")
    public void select_reason_for_cancellation(String cancellationReason, String returnType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        String dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";

        if(returnType.equals("CIT Return (Final)")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"FlexibleFormEntity:CITDetailsTab\"]/ul/li[6]/a"))).click();
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:CITDetailsTab:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();

        }
        if(returnType.equals("Capital Gains Tax Return")){
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();

        }
        if (returnType.equals("PAYE Returns")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("Excise Tax Return")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("FTT Return")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("GST Return")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("PIT Return (Final)")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:pitShowTab:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("PIT Return (Provisional)")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("Payroll Tax Return")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("Rental income Return")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }

        if (returnType.equals("WHT (10.5% and 5.5%) Return")) {
            dropdownXpath = "//*[@id=\"FlexibleFormEntity:reasonForCancellation\"]/div[3]";
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXpath))).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[contains(text(),'" + cancellationReason + "')]")).click();
        }
    }

    @Then("^Click cancel return$")
    public void click_cancel_return() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:cancel"))).click();
    }

    @Then("^Click yes$")
    public void click_yes() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:j_idt31"))).click();
    }

    @Then("^Click no$")
    public void click_no() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FlexibleFormEntity:j_idt32"))).click();
    }

    @Then("^Verify switch to page with url \"([^\"]*)\"$")
    public void verify_switch_to_page_with_url(String url) throws Throwable {
        Thread.sleep(2000);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, url);
    }

    //...............................................................PAYE CREDIT...............................................//
    @Then("^Click find to search for employee$")
    public void click_find_to_search_for_employee() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FormPayeCredit:FindTin"))).click();
    }

    @Then("^Search for employee using tin \"([^\"]*)\"$")
    public void search_for_employee_using_tin_something(String tin) {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(tin);
        driver.findElement(By.id("SearchForm:j_idt21")).click();
    }

    @Then("^Search for employer$")
    public void search_for_employer() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FormPayeCredit:search"))).click();
    }

    @Then("^Verify paye$")
    public void verify_paye() throws Throwable {
        Thread.sleep(4000);
        String totalPaye = driver.findElement(By.id("FormPayeCredit:totalPaye")).getText();

        if (totalPaye.equals("0.00")) {
            Assert.assertFalse("Could not search paye credit", false);
        }
    }

    //...............................................................print return reports....................................................................................................//
    @Then("^Select report to print \"([^\"]*)\"$")
    public void select_report_to_print(String reportType) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + reportType + "']"))).click();

    }

    @Then("^Select tax office \"([^\"]*)\"$")
    public void select_tax_office(String taxOffice) throws Throwable {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select business sector$")
    public void select_business_sector() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:BUSINESS_SECTOR\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select return type$")
    public void select_return_type() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:RETURN_TYPE\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select return type two$")
    public void select_return_type_two() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:TAX_TYPE\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select outcome status$")
    public void select_outcome_status() throws Throwable {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:OUTCOME_STATUS\"]/div[3]"))).click();
        Thread.sleep(1000);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select taxpayer category")
    public void select_taxpayer_category() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:Taxpayer_Category\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select taxpayer type")
    public void select_taxpayer_type() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:TAXPAYER_TYPE\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Select submission method")
    public void select_submission_method() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"frmReportDetails:SUBMISSION_METHOD\"]/div[3]"))).click();
        Thread.sleep(1500);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    }

    @Then("^Click run report \"([^\"]*)\"$")
    public void click_run_report(String buttonID) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(buttonID))).click();
    }

    @Then("^Enter start date as \"([^\"]*)\"$")
    public void enter_start_date(String startDate) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frmReportDetails:StartDate_input"))).sendKeys(startDate);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB).perform();
    }

    @Then("^Enter end date as today$")
    public void enter_end_date() throws Throwable{
        Thread.sleep(1000);
        driver.findElement(By.id("frmReportDetails:EndDate_input")).click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        action.sendKeys(Keys.TAB).perform();
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    @Then("^Verify file \"([^\"]*)\" has been downloaded in downloads directory \"([^\"]*)\"$")
    public void verify_file_has_been_downloaded_in_downloads_directory(String fileName, String downloadPath) throws Throwable {
        Thread.sleep(5000);
        if (isFileDownloaded(downloadPath, fileName)) {
            System.out.println(fileName + ": has been downloaded");
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(fileName + ": has not been downloaded", false);
        }
    }

    @Then("^Enter employer tin \"([^\"]*)\"$")
    public void enter_employer_tin(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frmReportDetails:TaxpayerTIN"))).sendKeys(tin);
    }

    @Then("^Enter tin nimber \"([^\"]*)\"$")
    public void enter_tin_number(String tin) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frmReportDetails:TIN_NUMBER"))).sendKeys(tin);
    }

    @Given("Open CRM URL for Returns Module")
    public void openCRMURLForReturnsModule() {
        driver.get(Pro.getProperty("CRM_URL"));
    }

    @And("Close Popup Window")
    public void closePopupWindow() {
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
        driver.switchTo().frame(specificframe);
        WebDriverWait CloseWindow=new WebDriverWait(driver,60);
        CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }

    @And("Click on Case management dropdown")
    public void clickOnCaseManagementDropdown() {
        driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a")).click();
    }

    @And("click on Returns Tax return application")
    public void clickOnReturnsTaxReturnApplication() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tbg_taxreturnapplication\"]"))).click();
    }

    @Then("switch to frame one")
    public void switchToFrameOne() throws Throwable{
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame1")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @Then("switch to frame zero")
    public void switchToFrameZero() throws Throwable{
        driver.switchTo().defaultContent();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contentIFrame0")));
        driver.switchTo().frame(specificframe);
        Thread.sleep(3000);
    }

    @When("enters reference number in search results")
    public void entersReferenceNumberInSearchResults() {
        WebDriverWait wait=new WebDriverWait(driver, 20);
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));
        search.sendKeys(ReferenceNumber);
//    	search.sendKeys("ACAD/000002717/2021");
        search.sendKeys(Keys.ENTER);

    }

    @When("Click selected Reference Number")
    public void clickSelectedReferenceNumber() throws Throwable{
        WebDriverWait wait=new WebDriverWait(driver, 30);
        WebElement elementLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + ReferenceNumber + "')]")));

        Actions actions = new Actions(driver);
        actions.doubleClick(elementLocator).perform();

        driver.switchTo().defaultContent();
        Thread.sleep(9000);
    }

    @And("clicks Approve from the dropdown")
    public void clicksApproveFromTheDropdown() throws Throwable{

        Thread.sleep(70000);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Actions action=new Actions(driver);
        WebElement Outcome=driver.findElement(By.id("header_process_tbg_approvaloutcome"));
        WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(7000);
        if(hasLoaded.isDisplayed()) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }else {
            action.doubleClick(Outcome).build().perform();
            Outcome.click();
            action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        }
    }

    @And("click save on returns")
    public void clickSaveOnReturns() throws Throwable{
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.findElement(By.id("tbg_taxreturnapplication|NoRelationship|Form|Mscrm.Form.tbg_taxreturnapplication.Save")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Then("Application Tax return Adjustment status should be {string}")
    public void applicationTaxReturnAdjustmentStatusShouldBe(String Status) throws Throwable{
        driver.switchTo().frame("contentIFrame1");
        WebDriverWait wait = new WebDriverWait(driver,200);
        WebElement statusLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'" + Status + "')]")));

        if (statusLabel.isDisplayed()) {
            Assert.assertTrue("Approved", true);
        } else {
            Assert.fail("Approval failed");
        }
        Thread.sleep(2000);
    }

    @Then("Confirm saved success message {string}")
    public void confirmSavedSuccessMessage(String SuccessMessage) {
        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + SuccessMessage + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Success message ('" + SuccessMessage + "') has been displayed");
            Assert.assertTrue(true);
        } else {
            Assert.fail("Tax could not be adjusted");
        }
    }

    @Then("Obtain reference number {string}")
    public void obtainReferenceNumber(String refno) throws Throwable{
        WebDriverWait wait = new WebDriverWait(driver,100);
        String text  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+refno+"')]"))).getText();
        System.out.println(text);
        System.out.println("substring is "+ text.substring(75));
        ReferenceNumber =text.substring(75);
        //Tax return has been successfully saved.The status is now pending approval. PAYER/000001429/2020
        // System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));
        System.out.println(ReferenceNumber);
        System.out.println("Actual ARN to be used in CRM is " +ReferenceNumber);

        Thread.sleep(5000);
    }

    @Then("Obtain reference number for cancellation {string}")
    public void obtainReferenceNumberForCancellation(String refno) throws Throwable{
        WebDriverWait wait = new WebDriverWait(driver,100);
        String text  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+refno+"')]"))).getText();
        System.out.println(text);
        System.out.println("substring is "+ text.substring(74));
        ReferenceNumber =text.substring(74);
        //Tax return has successfully saved.The status is now pending cancellation. CGTR/000002235/2021
        //System.out.println("Actual ARN to be used in CRM is "+"*"+text.substring(42));
        System.out.println(ReferenceNumber);
        System.out.println("Actual ARN to be used in CRM is " +ReferenceNumber);

        Thread.sleep(5000);
    }

    @Then("Verify and obtain ARN {string}")
    public void verifyARN(String arn) {
        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + arn + "')]")));
        String text  = successMessage.getText();
        System.out.println(text);
        System.out.println("substring is "+ text.substring(53));
        ReferenceNumber =text.substring(53);
        //Returns Lodgement is Successful with Reference Number FTTR/000002727/2021
        System.out.println(ReferenceNumber);
        if (successMessage.isDisplayed()) {
            System.out.println("ARN ('" + arn + "') is valid");
            Assert.assertTrue(true);
        } else {
            Assert.fail("ARN invalid");
        }
    }

    @Then("go to taxpayer accounting > taxpayer account inquiry")
    public void goToTaxpayerAccountingTaxpayerAccountInquiry() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[span='Taxpayer Accounting']"))).click();
        driver.findElement(By.xpath("//a[span='Taxpayer Account Enquiry']")).click();
    }

    @Then("Search for tin {string}")
    public void searchForTin(String tin) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchForm:accountNumber"))).sendKeys(tin);
        driver.findElement(By.id("SearchForm:j_idt40")).click();
    }

    @Then("Search for taxtype {string}")
    public void searchForTaxtype(String taxtype) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AccountEnquiry:TaxTypeAccount\"]/div[3]"))).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//li[contains(text(),'"+taxtype+"')]")).click();
        Thread.sleep(1500);
        driver.findElement(By.id("AccountEnquiry:j_idt64")).click();

    }


    @Then("Verify taxtype data is shown in table {string}")
    public void verifyTaxtypeDataIsShownInTable(String taxtype) {
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AccountEnquiry:periodicTable_data\"]/tr[1]/td[1]/div"))).click();
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + taxtype + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Taxtype ('" + taxtype + "') is shown");
            Assert.assertTrue(true);
        } else {
            Assert.fail("Taxtype not shown");
        }
    }

    @Then("Verify status is {string}")
    public void verifyStatusIs(String status) {
        WebDriverWait wait = new WebDriverWait(driver,60);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'" + status + "')]")));

        if (successMessage.isDisplayed()) {
            System.out.println("Status ('" + status + "') is shown");
            Assert.assertTrue(true);
        } else {
            Assert.fail("status not correct");
        }
    }

    @Then("Click on case")
    public void clickOnCase() {
        WebDriverWait wait = new WebDriverWait(driver,60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"AccountEnquiry:periodicTable_data\"]/tr[1]/td[1]/div"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + ReferenceNumber + "')]"))).click();
    }

    @Then("Verify lodgement screen has data")
    public void verifyLodgementScreenHasData() {
        WebDriverWait wait = new WebDriverWait(driver,60);
        String tin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ReturnsLodgement:id_Tin"))).getText();
        if(tin.isEmpty()){
            Assert.fail("Field does not contain any data");
        }
        else{
            Assert.assertTrue("Field contains data",!tin.isEmpty());
        }
    }
}