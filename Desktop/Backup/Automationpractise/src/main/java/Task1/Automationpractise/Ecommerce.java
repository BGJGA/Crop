package Task1.Automationpractise;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class Ecommerce {

    public static void main(String[] args) throws InterruptedException {
       // Initialize WebDriver (Chrome)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Open Flipkart
        driver.get("https://www.poorvika.com/");
 
        String url=driver.getCurrentUrl();
        String expectedurl="https://www.poorvika.com/";
        System.out.println(url);
        if(expectedurl.equals(url)) {
        	System.out.println("The url is loaded correctly");
        }
        else {
        	System.out.println("The url loaded is wrong ");
        }
        Thread.sleep(3000);
        
        String title=driver.getTitle();
        System.out.println(title);
        String expectedtitle="Shop Latest Electronic Appliances Online at Best Price | Poorvika";
        if(title.equals(expectedtitle)) {
        	System.out.println("Title is right");
        }
        else {
        	System.out.println("Title is wrong");
        }
         
        
     // Handle Popup Close
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='close-popup']")));
            closeButton.click(); // Close the popup
            System.out.println("Popup closed");
        } catch (Exception exception) {
            System.out.println("No popup found or failed to close");
        }
        
        
        
        
        
        
        // Login the website 
        WebElement signin=driver.findElement(By.xpath("//div[@class=\"app-bar_text__HwUaf\"]"));
        signin.click();
        
        WebElement login=driver.findElement(By.xpath("//button[@class=\"py-3 px-4 text-center w-full drop-shadow bg-white\"]"));
        login.click();
        
        // Enter the valid credentials
        WebElement mobileno=driver.findElement(By.id("userData"));
        mobileno.sendKeys("6374501223");
        String number=mobileno.getAttribute("value");
        String expectedno="6374501223";
        if(number.equals(expectedno)) {
        	System.out.println("The value entered is correct");
        }
        else {
        	System.out.println("The value entered is wrong");
        }
        Thread.sleep(3000);
        
        
        //Submit the form
        WebElement button=driver.findElement(By.xpath("//button[@class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]"));
           button.click();
           Thread.sleep(25000);
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the element to be visible
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("b.app-bar_name__Ix3jH")));

        System.out.println("Logged in user name: " + name);
           // Enter the product in search bar
           WebElement search=driver.findElement(By.cssSelector("input.text-black"));
           Actions actions=new Actions(driver);
           actions.sendKeys(search,"tempered glass");
           actions.sendKeys(Keys.ENTER).perform();
           Thread.sleep(3000);
           //Filter by price range
           WebElement pricerange=driver.findElement(By.xpath("//input[@type=\"number\" and @value=\"199\"]"));
           pricerange.clear();
           pricerange.sendKeys("199");
          
          
           WebElement gobutton=driver.findElement(By.xpath("//button[@type=\"button\" and text()=\"Go\"]"));
           gobutton.click();
           
           WebElement status=driver.findElement(By.xpath("//h1[text()=\"Stock Status\"]"));
           status.click();
           Thread.sleep(2000);
           WebElement check=driver.findElement(By.xpath("//label[@for=\"stock_status_In Stock\"]"));
           check.click();
           Thread.sleep(6000);
        WebElement product=driver.findElement(By.xpath("//img[@alt=\"2 5D Tempered Glass Screen Protector For Apple iPad Pro 10 9 Inch 10 Generation Transparent front view\"]"));
        product.click();
        Thread.sleep(3000);
     // Switch to the new tab
        String currentWindow = driver.getWindowHandle();  // Save the handle of the current window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle); // Switch to the new tab
               
            }
            }
                
        WebDriverWait waitForAddToCartButton = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = waitForAddToCartButton.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='Add To Cart'])[1]")));
        Thread.sleep(3000);
        WebElement cart=driver.findElement(By.cssSelector("svg.font-extrabold"));
        cart.click();
        Thread.sleep(3000);
        
        // Close the browser
            driver.quit();
      
    }
    }
