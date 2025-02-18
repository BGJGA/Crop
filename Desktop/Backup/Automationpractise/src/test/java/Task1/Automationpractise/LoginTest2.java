package Task1.Automationpractise;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import pages.Loginotp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.ITestResult;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LoginTest2 {
	private static final Logger logger = LoggerFactory.getLogger(LoginTest2.class);
	WebDriver driver;
	Loginotp loginotppage;
@BeforeClass(groups="login_otp")
public void driversetup() {
	try {
	driver=new ChromeDriver();
	logger.info("driver initialised");
	}
	catch(Exception exception) {
		logger.error("driver initialised error"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
	
}
@BeforeMethod(groups="login_otp")
public void websitesetup() {
	SoftAssert softAssert=new SoftAssert();
	try {
	loginotppage = new Loginotp(driver);
	driver.get("https://www.poorvika.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	String url=driver.getCurrentUrl();
	String expectedurl="https://www.poorvika.com/";
	softAssert.assertEquals(url,expectedurl,"Url loaded is incorrect");
	String titlename=driver.getTitle();
	String expectedtitle="Shop Latest Electronic Appliances Online at Best Price | Poorvika";
	softAssert.assertEquals(titlename,expectedtitle,"Title is incorrect");
	softAssert.assertAll();
	logger.info("website initialised");
	}
	catch(Exception exception) {
		logger.error("website intialised error"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="login_otp",description="Login using invalid mobile no",priority=0)
public void logintest01() {
	//Using invalid mobile no
	try {
	loginotppage.loginotpPage("1323453645797879");
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
     String message = popup.getText();
     Assert.assertEquals(message, "Please enter valid Mobile Number", "Popup message is incorrect");
     logger.info("Test passed for invalid mobile number by otp");}
	catch(Exception exception) {
		logger.error("Test failed for login using invalid mobile no"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="login_otp",description="Login using invalid email format",priority=1)
public void logintest02() {
	//Using invalid email format
	try {
	loginotppage.loginotpPage("balamurugan12iicsea");
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
     WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
		String message = popup.getText();
		Assert.assertEquals(message, "Please enter valid Email ID", "Popup message is incorrect");
		logger.info("Test passed for invalid email format login using otp");
		}
	catch(Exception exception) {
		logger.error("Test failed for login using invalid email format"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}



@AfterMethod(groups="login_otp")
public void screenshot(ITestResult result) throws IOException{
	try {
	File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String method=result.getMethod().getMethodName();
	//System.out.println(method);
	FileUtils.copyFile(screenshot, new File("./screenshots/"+method+"_screenshot.png"));}
	catch(Exception exception) {
		logger.error("Screenshot errorl"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
@AfterClass(groups="login_otp")
public void driverclose() {
	try {
	driver.quit();}
	catch(Exception exception) {
		logger.error("driver closing error"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
}
