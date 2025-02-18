package Task1.Automationpractise;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import pages.Logout;
import pages.AddToCart;
import pages.Search;
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
public class AddToCartTest {
	private static final Logger logger = LoggerFactory.getLogger(AddToCartTest.class);

	WebDriver driver;
	AddToCart addtocartpage;
	Logout logoutpage;
	
	@BeforeClass(groups="addtocart")
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
	@BeforeMethod(groups="addtocart")
	public void websitesetup() {
		SoftAssert softAssert=new SoftAssert();
		try {
		addtocartpage = new AddToCart(driver);
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

	@Test(groups="addtocart",description="search with product",priority=0)
	public void addtocart01() {
		
		//Using correct product
		try {
		addtocartpage.addtocart("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
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
	      Assert.assertEquals(message,"Item added to cart", "Popup message is incorrect");
	      WebElement cart=driver.findElement(By.cssSelector("svg.font-extrabold"));
	      cart.click();
	      WebElement cartproduct=driver.findElement(By.tagName("h2"));
	      String cartproductname=cartproduct.getText();
	      Assert.assertEquals(cartproductname,"2.5D Tempered Glass Screen Protector For Xiaomi Redmi Note 13/13 Pro ( Black )", "name is incorrect");
		logger.info("Test passed for add to cart");}
		catch(Exception exception) {
			logger.error("Test failed for add to cart button"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}


	}

	
	@AfterMethod(groups="addtocart")
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
	@AfterClass(groups="addtocart")
	public void driverclose() {
		try {
		driver.quit();}
		catch(Exception exception) {
			logger.error("Driver closing error"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
	}

}
