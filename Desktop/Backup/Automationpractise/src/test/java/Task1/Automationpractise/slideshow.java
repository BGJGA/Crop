package Task1.Automationpractise;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class slideshow {
public static void main(String[] args) {
	
	ChromeOptions options=new ChromeOptions();
	options.addArguments("--disable-notifications");
	WebDriver driver=new ChromeDriver(options);
	
	
	driver.manage().window().maximize();
	driver.get("https://www.boat-lifestyle.com/");
	
}
}
