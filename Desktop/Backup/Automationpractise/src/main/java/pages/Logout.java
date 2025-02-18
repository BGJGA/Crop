package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
public class Logout {

	WebDriver driver;
	public Logout(WebDriver driver) {
		this.driver=driver;
	}

	
	private By logoutbutton=By.xpath("//span[text()='Logout']");
	private By useraccount=By.cssSelector("div.app-bar_text__HwUaf");
	


	public void logout() {
		
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(useraccount)).perform();
		driver.findElement(logoutbutton).click();
				
				
		
	}
		
}
