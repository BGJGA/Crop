package Task1.Automationpractise;


//TestNg
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.IOException;

import java.time.Duration;


//Selenium 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoorvikaWebsiteTesting 
{
	
WebDriver driver;

private static final Logger logger = LoggerFactory.getLogger(PoorvikaWebsiteTesting.class);

@BeforeTest(groups={"cart","addtocart","login_otp","login_emailnpawd","search","register"})

public void driverinfo() {
	
	logger.info("Automation for Poorvika Website started");

}

@BeforeClass(groups={"cart","addtocart","login_otp","login_emailnpawd","search","register"})

public void driversetup() {
	try {
		
		driver=new ChromeDriver();
		logger.info("Driver setup intialised");
		
	}
	catch(Exception exception){
		
		logger.error("Error occured during driver setup"+exception.getMessage());
	
	}

}


@AfterClass(groups={"cart","addtocart","login_otp","login_emailnpawd","search","register"})
public void driverclose() {
	try {
		driver.quit();
		logger.info("Driver Closed");
	}
	catch(Exception exception)
	{
		logger.error("Error occured during closing the driver "+exception.getClass().getSimpleName());
	}
}
@BeforeMethod(groups={"cart","addtocart","login_otp","login_emailnpawd","search","register"})

public void websiteinit() {
	
	 SoftAssert softAssert = new SoftAssert();
	try 
	{
		
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.poorvika.com/");
	String url=driver.getCurrentUrl();
	String expectedurl="https://www.poorvika.com/";
	softAssert.assertEquals(url,expectedurl,"Url loaded is incorrect");
	String titlename=driver.getTitle();
	String expectedtitle="Shop Latest Electronic Appliances Online at Best Price | Poorvika";
	softAssert.assertEquals(titlename,expectedtitle,"Title is incorrect");
	softAssert.assertAll();
	}
	
	catch(Exception exception) {
		logger.error("Error occured during loading website"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexcepted conditions ");
	}
}


@Test(groups="login_otp", description="Login using invalid mobile number",priority=0)
public void logintest1() {
    // Using invalid mobile number
    try {
        WebElement signin = driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
        signin.click();
        WebElement login_otp = driver.findElement(By.xpath("//button[@class=\"py-3 px-4 text-center w-full drop-shadow bg-white\"]"));
        login_otp.click();
        WebElement mobileno = driver.findElement(By.id("userData"));
        mobileno.sendKeys("111111792792743");
        WebElement submitbutton = driver.findElement(By.xpath("//button[@class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]"));
        submitbutton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
        String message = popup.getText();
        Assert.assertEquals(message, "Please enter valid Mobile Number", "Popup message is incorrect");
        logger.info("Test passed for 'Login using for invalid mobile number'");

    } catch (Exception exception) {
        logger.error("Test failed for Login with invalid mobile number, "+exception.getClass().getSimpleName());
        Assert.fail("Test failed due to unexpected exception");  
    }
}

@Test(groups="login_emailnpawd",description="Login using valid email and password",priority=6)
public void logintest2() {
	//Using valid Email and valid password
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class=\"app-bar_app_bar__account__Eju9e text-right justify-center pt-1 ml-1\"]/div/b"), "bk"));
	     WebElement name=driver.findElement(By.xpath("//div[@class=\"app-bar_app_bar__account__Eju9e text-right justify-center pt-1 ml-1\"]/div/b"));
         String verify_name=name.getText();
         Assert.assertEquals(verify_name,"bk", "Login failed!");
         Actions actions=new Actions(driver);
         WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
         actions.moveToElement(user_acc).perform();
         WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
         WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
         logoutbutton.click();
         logger.info("Test passed for login with valid email and valid password");
	}
	catch(Exception exception) {
		 logger.error("Test failed for Login with valid email and valid password , "+exception.getClass().getSimpleName());
	     Assert.fail("Test failed due to unexpected exception");  
	}
}
@Test(groups="login_emailnpawd", description="Login using valid email and invalid password",priority=1)
public void logintest3() {
	//Using Email and invalid password
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234rtyu");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
         WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
 		String message = popup.getText();
 		Assert.assertEquals(message,"Sorry, User id (or) Password is invalid. Try to recover using Forgot Password (or) Login with OTP", "Popup message is incorrect");
        logger.info("Test passed for login for valid email and invalid password");    
	}
	catch(Exception exception) {
		logger.error("Test failed for vaild email and invalid password",exception.getClass().getSimpleName());
        Assert.fail("Test failed due to unexpected exception: ");
	}
}
@Test(groups="login_emailnpawd",description="Login using invalid email and valid password",priority=2)
public void logintest4() {
	//Using invalid Email and valid password
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("bal@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
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
@Test(groups="login_emailnpawd",description="Login using invalid email and invaid password",priority=3)
public void logintest5() {
	//Using invalid Email and invalid password
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("bal@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234asfg");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
         WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
 		String message = popup.getText();
 		Assert.assertEquals(message,"Sorry, User id (or) Password is invalid. Try to recover using Forgot Password (or) Login with OTP", "Popup message is incorrect");
	     logger.info("Test passed for invalid email and invalid password");
	}
	catch(Exception exception) {
		logger.error("Test failed for invalid email and invalid password"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
@Test(groups="login_emailnpawd",description="Login using empty email and empty password",priority=4)
public void logintest6() {
	SoftAssert softAssert = new SoftAssert();
	//Using empty Email and empty password
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
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
		logger.error("Test failed for empty email and empty password"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
@Test(groups="login_emailnpawd",description="Login using invalid email  format and valid password",priority=5)
public void logintest7() {
	//Using invalid Email format and valid password
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balfegtr");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
         WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
 		String message = popup.getText();
 		Assert.assertEquals(message,"Please enter valid Email ID", "Popup message is incorrect");
	    logger.info("Test passed for invalid email format and valid password"); 
	}
	catch(Exception exception) {
		logger.error("Test failed for invalid email format and valid password"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}
@Test(groups="login_otp", description="Login using invalid email format",priority=1)
public void logintest8() {
	//Using invalid email
	try {
		 WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();
	     WebElement login_otp=driver.findElement(By.xpath("//button[@class=\"py-3 px-4 text-center w-full drop-shadow bg-white\"]"));
	     login_otp.click();
	     WebElement mobileno=driver.findElement(By.id("userData"));
	     mobileno.sendKeys("sdsfdgeg");
         WebElement submitbutton=driver.findElement(By.xpath("//button[@class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]"));
         submitbutton.click(); 
         WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
         WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
 		String message = popup.getText();
 		Assert.assertEquals(message, "Please enter valid Email ID", "Popup message is incorrect");
        logger.info("Test passed for invalid email format in OTP button");
	}
	catch(Exception exception){
		logger.error("Test failed for invalid email format in otp button"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}




@Test(groups="register",description="Register with already registered user",priority=0)
public void register1() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();
		WebElement register=driver.findElement(By.cssSelector("span.login-register-form_form_options__register__CiRo9"));
		register.click();
		WebElement new_email=driver.findElement(By.id("userData"));
		new_email.sendKeys("balamurugang12iicsea@gmail.com");
		WebElement register_button=driver.findElement(By.xpath("//button[text()=\"REGISTER\" and @class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]"));
		register_button.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
		String message = popup.getText();
		Assert.assertEquals(message, "Email already exist", "Popup message is incorrect");
		logger.info("Test passed for register with already registered email");
	}
	catch(Exception exception) {
		logger.error("Test failed for register with already registered email"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="register",description="Register with invalid mobile number",priority=1)
public void register2() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();
		WebElement register=driver.findElement(By.cssSelector("span.login-register-form_form_options__register__CiRo9"));
		register.click();
		WebElement new_email=driver.findElement(By.id("userData"));
		new_email.sendKeys("11111772890087");
		WebElement register_button=driver.findElement(By.xpath("//button[text()=\"REGISTER\" and @class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]"));
		register_button.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
		String message = popup.getText();
		Assert.assertEquals(message, "Please enter valid Mobile Number", "Popup message is incorrect");
		logger.info("Test passed for register with invalid mobile number");
	}
	catch(Exception exception) {
		logger.error("Test failed for register with invalid mobile number"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}


@Test(groups="register",description="Register with invalid email format",priority=2)
public void register3() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();
		WebElement register=driver.findElement(By.cssSelector("span.login-register-form_form_options__register__CiRo9"));
		register.click();
		WebElement new_email=driver.findElement(By.id("userData"));
		new_email.sendKeys("dgjhjgjkl");
		WebElement register_button=driver.findElement(By.xpath("//button[text()=\"REGISTER\" and @class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]"));
		register_button.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
		String message = popup.getText();
		Assert.assertEquals(message, "Please enter valid Email ID", "Popup message is incorrect");
		logger.info("Test passed for register with invalid email format");

	}
	catch(Exception exception) {
		logger.error("Test failed for register with invalid email format"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="search",description="Search the product", priority=0)
public void searchtest1() {
	SoftAssert softAssert=new SoftAssert();
	// search the product
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
        search.sendKeys("tempered glass");
       WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
       searchbutton.click();
     softAssert.assertTrue(searchbutton.isEnabled(), "button is disabled");
     WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(10));
     WebElement product=wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt=\"2 5D Tempered Glass Screen Protector For Apple iPad Pro 10 9 Inch 10 Generation Transparent front view\"]")));
     product.click();
     String currentwindow = driver.getWindowHandle();  
     for (String windowhandle : driver.getWindowHandles()) {
         if (!windowhandle.equals(currentwindow)) {
             driver.switchTo().window(windowhandle);   
         }
         }
     WebElement productname=driver.findElement(By.tagName("h1"));
     String name=productname.getText();
     softAssert.assertEquals(name,"2.5D Tempered Glass Screen Protector For Apple iPad Pro 10.9 Inch 10th Generation ( Transparent )", "searched product not displayed");
     Actions actions=new Actions(driver);
     WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
     actions.moveToElement(user_acc).perform();
     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     WebElement logoutbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
    logoutbutton.click();
     softAssert.assertAll(); 
     logger.info("Test passed for search the product");
     }
	catch(Exception exception) {
		logger.error("Test failed for search the product"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}



@Test(groups="search",description="search with price range filter",priority=1)
public void searchtest2() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
       search.sendKeys("tempered glass");
      WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
      searchbutton.click();
      WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
      WebElement pricerange=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type=\"number\" and @value=\"199\"]")));//By.xpath("//input[@type=\"number\" and @value=\"199\"]"));
      pricerange.clear();
      pricerange.sendKeys("199");
      WebElement gobutton=driver.findElement(By.xpath("//button[@type=\"button\" and text()=\"Go\"]"));
      gobutton.click();
      WebElement product=driver.findElement(By.xpath("//img[@alt=\"2 5d tempered glass screen protector for xiaomi redmi note 13 13 pro black front view\"]"));
      product.click();
      String currentwindow = driver.getWindowHandle();  
      for (String windowhandle : driver.getWindowHandles()) {
          if (!windowhandle.equals(currentwindow)) {
              driver.switchTo().window(windowhandle);   
          }
          }
      WebElement price=driver.findElement(By.xpath("//b[contains(text(),'₹ 199')]"));
      String pricevalue=price.getText();
      Assert.assertEquals(pricevalue, "₹ 199","PriceFilter didn't work");
      Actions actions=new Actions(driver);
      WebElement cartproduct=driver.findElement(By.tagName("h1"));
      String cartproductname=cartproduct.getText();
      Assert.assertEquals(cartproductname,"2.5D Tempered Glass Screen Protector For Xiaomi Redmi Note 13/13 Pro ( Black )", "name is incorrect");
      
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
     logger.info("Test passed for search with price range filter"); 
	}
	catch(Exception exception) {
		logger.error("Test failed for search with price range filter"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="search",description="search with color filter",priority=2)
public void searchtest3() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
       search.sendKeys("tempered glass");
      WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
      searchbutton.click();
      WebElement color=driver.findElement(By.xpath("//h1[text()=\"COLOR\"]"));
      color.click();
      List<WebElement> list=driver.findElements(By.xpath("//input[@id=\"color-search\"]/following-sibling::ul/li/input"));
      for(WebElement check:list)
      {  
    	if(!check.isSelected()) {
    		check.click(); 
    		}  
      }      
      WebElement product=driver.findElement(By.xpath("//img[@alt=\"2 5d tempered glass screen protector for xiaomi redmi note 13 13 pro black front view\"]"));
      product.click();
      String currentwindow = driver.getWindowHandle();  
      for (String windowhandle : driver.getWindowHandles()) {
          if (!windowhandle.equals(currentwindow)) {
              driver.switchTo().window(windowhandle);   
          }
          }
      WebElement price=driver.findElement(By.xpath("//b[contains(text(),'₹ 199')]"));
      String pricevalue=price.getText();
      Assert.assertEquals(pricevalue, "₹ 199","PriceFilter didn't work");
      Actions actions=new Actions(driver);
      WebElement cartproduct=driver.findElement(By.tagName("h1"));
      String cartproductname=cartproduct.getText();
      Assert.assertEquals(cartproductname,"2.5D Tempered Glass Screen Protector For Xiaomi Redmi Note 13/13 Pro ( Black )", "name is incorrect");
    
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
      logger.info("Test passed for search with color filter");
      
	}
	catch(Exception exception) {
		logger.error("Test failed for search with color filter"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="search",description="search with status",priority=3)
public void searchtest4() {
	//search the product with status
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
       search.sendKeys("tempered glass");
      WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
      searchbutton.click();
      WebElement status=driver.findElement(By.xpath("//h1[text()=\"Stock Status\"]"));
      status.click();
      WebElement check_stock=driver.findElement(By.xpath("//label[@for=\"stock_status_In Stock\"]"));
      check_stock.click();
      Thread.sleep(3000);
      WebElement product=driver.findElement(By.xpath("//img[@alt=\"2 5d tempered glass screen protector for xiaomi redmi note 13 13 pro black front view\"]"));
      product.click();
      String currentwindow = driver.getWindowHandle();  
      for (String windowhandle : driver.getWindowHandles()) {
          if (!windowhandle.equals(currentwindow)) {
              driver.switchTo().window(windowhandle);   
          }
          }
      WebElement price=driver.findElement(By.xpath("//b[contains(text(),'₹ 199')]"));
      String pricevalue=price.getText();
      Assert.assertEquals(pricevalue, "₹ 199","status didn't work");
      WebElement cartproduct=driver.findElement(By.tagName("h1"));
      String cartproductname=cartproduct.getText();
      Assert.assertEquals(cartproductname,"2.5D Tempered Glass Screen Protector For Xiaomi Redmi Note 13/13 Pro ( Black )", "name is incorrect");
      Actions actions=new Actions(driver);
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
      logger.info("Test passed with status filter");
	}
	catch(Exception exception) {
		logger.error("Test failed for empty status filter"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="search",description="search with wrong keyword",priority=4)
public void searchtest5() {
	//search the product with status
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
       search.sendKeys("jhjkjdhehawjl");
      WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
      searchbutton.click();
      WebElement error=driver.findElement(By.xpath("//h1[text()=\"Page not found\"]"));
      String message=error.getText();
      System.out.println(message);
      Assert.assertEquals(message,"Page Not Found","Page not found not displayed");
      Actions actions=new Actions(driver);
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
      logger.info("Test passed with status filter");
	}
	catch(Exception exception) {
		logger.error("Test failed for wrong keyword"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}


@Test(groups="addtocart",description="add to cart button",priority=0)
public void addtocart1() {
	//add to cart button 
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
       search.sendKeys("tempered glass");
      WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
      searchbutton.click();
      WebElement product=driver.findElement(By.xpath("//img[@alt=\"2 5d tempered glass screen protector for xiaomi redmi note 13 13 pro black front view\"]"));
      product.click();
      String currentwindow = driver.getWindowHandle();  
      for (String windowhandle : driver.getWindowHandles()) {
          if (!windowhandle.equals(currentwindow)) {
              driver.switchTo().window(windowhandle);   
          }
          }
      WebElement price=driver.findElement(By.xpath("//b[contains(text(),'₹ 199')]"));
      String pricevalue=price.getText();
      Assert.assertEquals(pricevalue, "₹ 199","PriceFilter didn't work");
      WebDriverWait waitForAddToCartButton = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement addToCartButton = waitForAddToCartButton.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Add To Cart\"]")));
      addToCartButton.click();
      WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
      WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
      String message = popup.getText();
      System.out.println(message);
      Assert.assertEquals(message,"Item added to cart", "Popup message is incorrect");
      WebElement cart=driver.findElement(By.cssSelector("svg.font-extrabold"));
      cart.click();
      WebElement cartproduct=driver.findElement(By.tagName("h2"));
      String cartproductname=cartproduct.getText();
      Assert.assertEquals(cartproductname,"2.5D Tempered Glass Screen Protector For Xiaomi Redmi Note 13/13 Pro ( Black )", "name is incorrect");
      WebElement remove=driver.findElement(By.xpath("//button[@class=\"flex items-center\"]/span[text()=\"Remove\"]"));
      remove.click();   
      Actions actions=new Actions(driver);
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
      logger.info("Test passed for add to cart button ");
	}
	catch(Exception exception) {
		logger.error("Test failed for add to cart button "+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="cart",description="cart icon with selected product",priority=2)
public void cart1() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
       search.sendKeys("tempered glass");
      WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
      searchbutton.click();
      WebElement product=driver.findElement(By.xpath("//img[@alt=\"Cool touch 6d tempered glass screen protector for oppo f25 pro 5g black Front View\"]"));
      product.click();
      String currentwindow = driver.getWindowHandle();  
      for (String windowhandle : driver.getWindowHandles()) {
          if (!windowhandle.equals(currentwindow)) {
              driver.switchTo().window(windowhandle);   
          }
          }
      WebElement price=driver.findElement(By.xpath("//b[contains(text(),'₹ 299')]"));
      String pricevalue=price.getText();
      Assert.assertEquals(pricevalue, "₹ 299","Price didn't match");
      WebDriverWait waitForAddToCartButton = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement addToCartButton = waitForAddToCartButton.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Add To Cart\"]")));
      addToCartButton.click();
      WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
      WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
      String message = popup.getText();
      System.out.println(message);
      Assert.assertEquals(message,"Item added to cart", "Popup message is incorrect");
      WebElement cart=driver.findElement(By.cssSelector("svg.font-extrabold"));
      cart.click();
      WebElement cartproduct=driver.findElement(By.tagName("h2"));
      String cartproductname=cartproduct.getText();
      Assert.assertEquals(cartproductname,"Cool Touch 6D Tempered Glass Screen Protector For Oppo F25 Pro 5G ( Black )", "name is incorrect");
//      WebElement remove=driver.findElement(By.xpath("//button[@class=\"flex items-center\"]/span[text()=\"Remove\"]"));
//      remove.click();      
      Actions actions=new Actions(driver);
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
      logger.info("Test passed for cart icon");
	}
	catch(Exception exception) {
		logger.error("Test failed for cart icon"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="cart",description="cart icon with empty cart",priority=0)
public void cart2() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
      WebElement cart=driver.findElement(By.cssSelector("svg.font-extrabold"));
      cart.click();
      WebElement cartmessage=driver.findElement(By.xpath("//h1[text()=\"Your shopping cart is empty!\"]"));
      String message=cartmessage.getText();
      Assert.assertEquals(message,"Your shopping cart is empty!", "cart is not empty");
      Actions actions=new Actions(driver);
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
      logger.info("Test passed for cart icon with empty cart");
	}
	catch(Exception exception) {
		logger.error("Test failed for cart icon with empty cart "+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}

@Test(groups="cart",description="cart icon with remove button functionality ",priority=1)
public void cart3() {
	try {
		WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
	     signin.click();	
	     WebElement email=driver.findElement(By.id("userData"));
	     email.sendKeys("balamurugang12iicsea@gmail.com");
	     WebElement password=driver.findElement(By.id("password"));
	     password.sendKeys("asdf1234");
	     WebElement loginbutton=driver.findElement(By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]"));
	     loginbutton.click();
		WebElement search=driver.findElement(By.cssSelector("input.text-black"));
       search.sendKeys("tempered glass");
      WebElement searchbutton=driver.findElement(By.cssSelector("button.app-bar_search_desktop__BRcZg"));
      searchbutton.click();
      WebElement product=driver.findElement(By.xpath("//img[@alt=\"2 5d tempered glass screen protector for oppo f27 5g black Front View\"]"));
      product.click();
      String currentwindow = driver.getWindowHandle();  
      for (String windowhandle : driver.getWindowHandles()) {
          if (!windowhandle.equals(currentwindow)) {
              driver.switchTo().window(windowhandle);   
          }
          }
      WebElement productname=driver.findElement(By.xpath("//h1[@class=\"center-content_product_name__O9C4s\"]"));
      String name=productname.getText();
      Assert.assertEquals(name, "2.5D Tempered Glass Screen Protector For Oppo F27 5G ( Black )","Product name didn't match");
      WebDriverWait waitForAddToCartButton = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement addToCartButton = waitForAddToCartButton.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Add To Cart\"]")));
      addToCartButton.click();
      WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
      WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("notification_notifyWrapper__xYXL9")));
      String message = popup.getText();
      Assert.assertEquals(message,"Item added to cart", "Popup message is incorrect");
      WebElement cart=driver.findElement(By.cssSelector("svg.font-extrabold"));
      cart.click();
      WebElement cartproduct=driver.findElement(By.tagName("h2"));
      String cartproductname=cartproduct.getText();
      Assert.assertEquals(cartproductname,"2.5D Tempered Glass Screen Protector For Oppo F27 5G ( Black )", "name is incorrect");
      WebElement remove=driver.findElement(By.xpath("//button[@class=\"flex items-center\"]/span[text()=\"Remove\"]"));
      remove.click();
      WebElement cartmessage=driver.findElement(By.xpath("//h1[text()=\"Your shopping cart is empty!\"]"));
      String textmessage=cartmessage.getText();
      Assert.assertEquals(textmessage,"Your shopping cart is empty!", "cart is not empty");
      Actions actions=new Actions(driver);
      WebElement user_acc=driver.findElement(By.cssSelector("div.app-bar_text__HwUaf")); 
      actions.moveToElement(user_acc).perform();
      WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement logoutbutton = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Logout']")));
      logoutbutton.click();
      logger.info("Test passed for cart icon with remove functionality ");
	}
	catch(Exception exception) {
		logger.error("Test failed for cart icon"+exception.getClass().getSimpleName());
		Assert.fail("Test failed due to unexpected conditions");
	}
}



@AfterMethod(groups={"cart","addtocart","login_otp","login_emailnpawd","search","register"})
public void testscreenshot(ITestResult result) throws IOException, InterruptedException{
	File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String method=result.getMethod().getMethodName();
	//System.out.println(method);
	FileUtils.copyFile(screenshot, new File("./screenshots/"+method+"_screenshot.png"));
	
}

@AfterTest(groups={"cart","addtocart","login_otp","login_emailnpawd","search","register"})
public void testinfo() {
	logger.info("Test Completed");
}
}
