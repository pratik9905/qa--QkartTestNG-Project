package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */
            WebElement addAdress = driver.findElement(By.className("css-rfvjbl"));
            Thread.sleep(3000);
            addAdress.click();
            Thread.sleep(3000);

            WebElement addAdressTextArea = driver.findElement(By.className("css-u36398"));
            Thread.sleep(3000);
            addAdressTextArea.sendKeys(addresString);
            Thread.sleep(3000);

            WebElement addNewAdressButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div[2]/button[1]"));
            Thread.sleep(3000);
            addNewAdressButton.click();
            Thread.sleep(3000);



            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            Thread.sleep(3000);
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            List<WebElement> selectAdd = driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/div[1]/p"));
            for(WebElement AddRadio:selectAdd){
                String actualAdd=AddRadio.getText();//div[@class='MuiBox-root css-0']//input
               
                if(actualAdd.equals(addressToSelect)){
                    Thread.sleep(3000);
                    AddRadio.click();
                    return true;
                }
            }
            System.out.println("Unable to find the given address");
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement palceOrderButton = driver.findElement(By.className("css-177pwqq"));
            Thread.sleep(3000);
            palceOrderButton.click();

            return true;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6
            WebElement insuffMessElement  = driver.findElement(By.xpath("//*[@id='notistack-snackbar']"));
            if(insuffMessElement.isDisplayed()){
                System.out.println("insufficient is displayed");
                return true;
            }

            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
