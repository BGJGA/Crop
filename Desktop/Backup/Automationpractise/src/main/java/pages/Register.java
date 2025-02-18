package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class Register {

	WebDriver driver;
	public Register(WebDriver driver) {
		this.driver=driver;
	}

	private By signin=By.xpath("//div[@class=\"app-bar_text__HwUaf\"]");
	private By register=By.cssSelector("span.login-register-form_form_options__register__CiRo9");
	private By userdataBy=By.id("userData");
	private By registerbutton=By.xpath("//button[text()=\"REGISTER\" and @class=\"button bg-blue-500 hover:bg-blue-700 text-white font-bold py-3 px-6 width:auto undefined button--orange-gradient button_button--orange-gradient__FAFeb button_btnClass__TjSbM false\"]");


	public void register(String data) {
		
		driver.findElement(signin).click();
		driver.findElement(register).click();
		driver.findElement(userdataBy).sendKeys(data);
		driver.findElement(registerbutton).click();
	}
		
	
}
