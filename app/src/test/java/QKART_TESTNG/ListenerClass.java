package QKART_TESTNG;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass implements ITestListener{
    
     RemoteWebDriver driver = QKART_Tests.driver;
     

    public static void takeScreenshot(WebDriver driver, String screenshotType, String description) {
        try {
            File theDir = new File("/screenshots");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            String timestamp = String.valueOf(java.time.LocalDateTime.now());
            String fileName = String.format("screenshot_%s_%s_%s.png", timestamp, screenshotType, description);
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("screenshots/" + fileName);
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
@Override
public void onStart(ITestContext context) {
   
    System.out.println("onStart method Started");
}

@Override
public void onTestStart(ITestResult result) {
    takeScreenshot(driver, "StartTestCase", result.getName());
}
@Override
public void onTestFailure(ITestResult result) {
    takeScreenshot(driver, "TestCaseFailure", result.getName());
}

@Override
public void onTestSuccess(ITestResult result) {
    takeScreenshot(driver, "TestCaseSuccess", result.getName());
}

@Override
public void onFinish(ITestContext context) {
    System.out.println("onFinish method Started");
}

}