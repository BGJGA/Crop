package Task1.Automationpractise;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class tb {
public static void main(String[] args) {
	WebDriver driver=new ChromeDriver();
	driver.get("https://www.boat-lifestyle.com/");
	WebElement div= driver.findElement(By.cssSelector("div.MultiCarousel-inner.template--14357041578082__e4e83c48-4e49-4d76-a45b-e25f3061707b.draggable.MultiCarousel_loaded > div:active"));
	div.click();
	
}
}
