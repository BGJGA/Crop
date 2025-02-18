package Task1.Automationpractise;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import pages.Logout;
import pages.Cart;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CartTest {
	private static final Logger logger = LoggerFactory.getLogger(CartTest.class);

	WebDriver driver;
	Cart cartpage;
	Logout logoutpage;
	@BeforeClass(groups="cart")
	public void driversetup() {
		try {
		driver=new ChromeDriver();
		logger.info("Driver initialised");
		}
		catch(Exception exception) {
			logger.error("Driver initialisation error"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		
	}
	@BeforeMethod(groups="cart")
	public void websitesetup() {
		SoftAssert softAssert=new SoftAssert();
		try {
		cartpage = new Cart(driver);
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
		logger.info("Website initialised");}
		catch(Exception exception) {
			logger.error("Website initialisation error"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
	}

	@Test(groups="cart",description="cart is empty",priority=0)
	public void cart01() {
		
		//cart is empty
		try {
		cartpage.cart("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
		 WebElement cart=driver.findElement(By.cssSelector("svg.font-extrabold"));
	      cart.click();
	      WebElement cartmessage=driver.findElement(By.xpath("//h1[text()=\"Your shopping cart is empty!\"]"));
	      String message=cartmessage.getText();
	      Assert.assertEquals(message,"Your shopping cart is empty!", "cart is not empty");
		logger.info("Test passed for cart is empty");}
		catch(Exception exception) {
			logger.error("Test failed for cart is empty"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}


	}
	
	@Test(groups="cart",description="cart with remove button",priority=1)
	public void cart02() {
		
		//cart with remove button
		try {
		cartpage.cart("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
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
	    logger.info("Test passed for cart with remove functionality");  
		}
		catch(Exception exception) {
			logger.error("Test failed for cart with remove functionality"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}


	}

	@Test(groups="cart",description="cart with product",priority=1)
	public void cart03() {
		
		//cart with product
		try {
		cartpage.cart("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
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
	    logger.info("Test passed for cart with product");  
		}
		catch(Exception exception) {
			logger.error("Test failed for cart with product"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}


	}
	
	@AfterMethod(groups="cart")
	public void screenshot(ITestResult result) throws IOException{
		try {
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String method=result.getMethod().getMethodName();
		//System.out.println(method);
		FileUtils.copyFile(screenshot, new File("./screenshots/"+method+"_screenshot.png"));}
		catch(Exception exception) {
			logger.error("Screenshot error"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
	}
	@AfterClass(groups="cart")
	public void driverclose() {
		try {
		driver.quit();
		logger.info("Driver session closed");
		}
		catch(Exception exception) {
			logger.error("Driver closing error"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
	}

}
