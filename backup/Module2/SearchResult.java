package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.MouseAction.Button;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        titleOfSearchResult=parentElement.findElement(By.xpath("//div/p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']")).getText();

        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizeChartLink = parentElement.findElement(By.tagName("Button"));
            sizeChartLink.click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            Thread.sleep(2000);
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
            WebElement chartElement = parentElement.findElement(By.tagName("button"));
            System.out.println(chartElement.getText());
            status=chartElement.getText().equals("SIZE CHART");
            System.out.println(status);
            Thread.sleep(2000);
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        
        try {
            Thread.sleep(2000);
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */

             WebElement parentSizeChartElement = driver.findElement(By.className("css-uhb5lp"));
             WebElement tableElement = parentSizeChartElement.findElement(By.tagName("table"));
             List<WebElement> tableHeader = tableElement.findElement(By.tagName("thead")).findElements(By.tagName("th"));

             String tableHeaderValue;
             for(int i =0;i<expectedTableHeaders.size();i++){
                tableHeaderValue=tableHeader.get(i).getText();
                System.out.println(tableHeaderValue);
                System.out.println(expectedTableHeaders.get(i));;

                if(!expectedTableHeaders.get(i).equals(tableHeaderValue)){
                     status= false;
                }

             }
             Thread.sleep(2000);

             List<WebElement> tableBodyElements = tableElement.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

             for(int i=0;i<expectedTableBody.size();i++){
                for(int j=1;j<=expectedTableBody.get(i).size();j++){

                    // System.out.println(tableBodyElements.get(i));
                    // System.out.println(tableBodyElements.get(i).findElement(By.tagName("td["+j+"]")).getText());
                    // System.out.println(expectedTableBody.get(i).get(j-1));

                    if(!tableBodyElements.get(i).findElement(By.xpath("td["+j+"]")).getText().equalsIgnoreCase(expectedTableBody.get(i).get(j-1))){
                        return false;
                    }
                }
             }


            return status;
        
        } catch (Exception e) {
            System.out.println("Error while validating chart contents" + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            status=parentElement.findElement(By.xpath("../div[2]/div")).isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}