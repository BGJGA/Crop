package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class Loginotp {
	WebDriver driver;
	public Loginotp(WebDriver driver) {
		this.driver=driver;
	}

	private By signin=By.xpath("//div[@class=\"app-bar_text__HwUaf\"]");
	private By loginotp=By.xpath("//button[@class=\"py-3 px-4 text-center w-full drop-shadow bg-white\"]");
	private By mobilenoBy=By.id("userData");
	private By loginbutton=By.xpath("//button[@class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]");


	public void loginotpPage(String mobileno) {
		
		driver.findElement(signin).click();
		driver.findElement(loginotp).click();
		driver.findElement(mobilenoBy).sendKeys(mobileno);
		driver.findElement(loginbutton).click();
	}
		
	
}
