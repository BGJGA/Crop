package Task1.Automationpractise;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import pages.Logout;
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
public class SearchTest {
	private static final Logger logger = LoggerFactory.getLogger(SearchTest.class);

	WebDriver driver;
	Search searchpage;
	Logout logoutpage;
	
	@BeforeClass(groups="search")
	public void driversetup() {
		try {
		driver=new ChromeDriver();}
		catch(Exception exception) {
			logger.error("Driver initialisation error"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		
	}
	@BeforeMethod(groups="search")
	public void websitesetup() {
		SoftAssert softAssert =new SoftAssert();
		try {
		searchpage = new Search(driver);
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

	@Test(groups="search",description="search with product",priority=0)
	public void search01() {
		SoftAssert softAssert=new SoftAssert();
		//Using correct product
		try {
		searchpage.search("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
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
		logger.info("Test passed for search with product");}
		catch(Exception exception) {
			logger.error("Test failed for search with correct product"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}

	}

	@Test(groups="search",description="search with price filter",priority=1)
	public void search02() {
		//search with price filter
		try {
			searchpage.search("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
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
			logger.info("Test passed for search with product");}
			catch(Exception exception) {
				logger.error("Test failed for search with price filter"+exception.getClass().getSimpleName());
				Assert.fail("Test failed due to unexpected conditions");
			}
		finally {
			logoutpage.logout();
		}

}



	@Test(groups="search",description="search with color filter",priority=2)
	public void search03() {
		//search with color filter
		try {
			searchpage.search("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
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
		logger.info("Test passed for search with color filter");
			}
		catch(Exception exception) {
			logger.error("Test failed for search with color filter"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}

	
	}
	
	@Test(groups="search",description="search with status",priority=3)
	public void search04() {
		//search with status
		try {
			searchpage.search("balamurugang12iicsea@gmail.com","asdf1234","tempered glass");
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
		logger.info("Test passed for search with status filter");
			}
		catch(Exception exception) {
			logger.error("Test failed for search with status filter"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}
	
	}
	
	@Test(groups="search",description="search with wrong keyword",priority=4)
	public void search05() {
		//search with status
		try {
			searchpage.search("balamurugang12iicsea@gmail.com","asdf1234","fdhyhdhswgery");
			WebElement error=driver.findElement(By.xpath("//h1[text()=\"Page not found\"]"));
		      String message=error.getText();
		      System.out.println(message);
		      Assert.assertEquals(message,"Page Not Found","Page not found not displayed");
		logger.info("Test passed for search with wrong keyword");
			}
		catch(Exception exception) {
			logger.error("Test failed for search with wrong keyword"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
		finally {
			logoutpage.logout();
		}
	
	
	}
	
	@AfterMethod(groups="search")
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
	@AfterClass(groups="search")
	public void driverclose() {
		try {
		driver.quit();}
		catch(Exception exception) {
			logger.error("Driver closing error"+exception.getClass().getSimpleName());
			Assert.fail("Test failed due to unexpected conditions");
		}
	}

}
