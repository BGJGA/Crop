package Task1.Automationpractise;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import pages.Login;
import pages.Logout;
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

public class LoginTest1 {
	WebDriver driver;
	Login loginpage;
	Logout logoutpage;
	private static final Logger logger = LoggerFactory.getLogger(LoginTest1.class);

@BeforeClass(groups="login_email")
public void driversetup() {
	try {
	driver=new ChromeDriver();
	logger.info("Driver initialised");
	}
	catch(Exception exception) {
		logger.error("Test failed for driver intialisation"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
	
}
@BeforeMethod(groups="login_email")
public void websitesetup() {
	SoftAssert softAssert = new SoftAssert();
	try {
	loginpage = new Login(driver);
	logoutpage=new Logout(driver);
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
		logger.error("Test failed for website intialisation"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="login_email",description="Login using invalid email and invalid password",priority=0)
public void logintest01() {
	//Using invalid email and invalid password
	try {
	loginpage.loginPage("adfds@gmail.com","fdsdfs");
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
	String message = popup.getText();
	Assert.assertEquals(message,"Sorry, User id (or) Password is invalid. Try to recover using Forgot Password (or) Login with OTP", "Popup message is incorrect");
	logger.info("Test passed for login using invalid email and invalid password ");
	}
	catch(Exception exception) {
		logger.error("Test failed for login using invalid email and invalid password"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="login_email",description="Login using invalid email format and valid password",priority=1)
public void logintest02() {
	//Using invalid email format and valid password
	try {
	loginpage.loginPage("balamurugan12iicsea","asdf1234");
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
     WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
		String message = popup.getText();
		Assert.assertEquals(message,"Please enter valid Email ID", "Popup message is incorrect");
		logger.info("Test passed for invalid email format and valid password ");
	}
	catch(Exception exception) {
		logger.error("Test failed for invalid email format and valid password"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}}

@Test(groups="login_email",description="Login using empty email and empty password",priority=2)
public void logintest03() {
	SoftAssert softAssert=new SoftAssert();
	//Using empty email and empty password
	try {
	loginpage.loginPage("","");
	WebElement validationemail=driver.findElement(By.xpath("//input[@id=\"userData\"]/following-sibling::div"));
    String verifyemail=validationemail.getText();
    softAssert.assertEquals(verifyemail,"Email / Mobile Number is required", "popup is incorrect");
    WebElement validationpassword=driver.findElement(By.xpath("//input[@id=\"password\"]/following-sibling::div"));
    String verifypassword=validationpassword.getText();
    softAssert.assertEquals(verifypassword,"Password is required", "popup is incorrect");
    softAssert.assertAll();
    logger.info("Test passed for empty email and empty password");
	}
	catch(Exception exception) {
		logger.error("Test failed for empty email and empty password a"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
@Test(groups="login_email",description="Login using valid email and invalid password",priority=2)
public void logintest04() {
	//Using valid email and invalid password
	try {
	loginpage.loginPage("balamurugang12iicsea@gmail.com","fdsdf12s");
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
     WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
	String message = popup.getText();
	Assert.assertEquals(message,"Sorry, User id (or) Password is invalid. Try to recover using Forgot Password (or) Login with OTP", "Popup message is incorrect");
	logger.info("Test passed for login using valid emai and password");}
	catch(Exception exception) {
		logger.error("Test failed for login using valid email and invalid password "+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}}

@Test(groups="login_email",description="Login using invalid email and valid password",priority=3)
public void logintest05() {
	//Using invalid email and valid password
	try {
	loginpage.loginPage("aqdad@gmail.com","asdf1234");
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
	String message = popup.getText();
	Assert.assertEquals(message,"Sorry, User id (or) Password is invalid. Try to recover using Forgot Password (or) Login with OTP", "Popup message is incorrect");
	logger.info("Test passed for invalid email and valid password");
	}
	catch(Exception exception) {
		logger.error("Test failed for invalid email and valid password"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="login_email",description="Login using valid email and valid password",priority=4)
public void logintest06() {
	//Using valid email and valid password
	try {
	loginpage.loginPage("balamurugang12iicsea@gmail.com","asdf1234");
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class=\"app-bar_app_bar__account__Eju9e text-right justify-center pt-1 ml-1\"]/div/b"), "bk"));
    WebElement name=driver.findElement(By.xpath("//div[@class=\"app-bar_app_bar__account__Eju9e text-right justify-center pt-1 ml-1\"]/div/b"));
    String verify_name=name.getText();
    Assert.assertEquals(verify_name,"bk", "Login failed!");
    logger.info("Test passed for valid email and valid password");
	}
	catch(Exception exception) {
		logger.error("Test failed for valid email and valid password "+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
	finally {
		logoutpage.logout();
	}
}

@AfterMethod(groups="login_email")
public void screenshot(ITestResult result) throws IOException{
	try {
	File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String method=result.getMethod().getMethodName();
	//System.out.println(method);
	FileUtils.copyFile(screenshot, new File("./screenshots/"+method+"_screenshot.png"));}
	catch(Exception exception) {
		logger.error("screenshot error"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
@AfterClass(groups="login_email")
public void driverclose() {
	try {
	driver.quit();}
	catch(Exception exception) {
		logger.error("driver closing error"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
}
