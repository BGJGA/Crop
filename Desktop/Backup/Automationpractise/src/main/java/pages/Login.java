package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
public class Login {
WebDriver driver;
public Login(WebDriver driver) {
	this.driver=driver;
}

private By signin=By.xpath("//div[@class=\"app-bar_text__HwUaf\"]");
private By usernameBy=By.id("userData");
private By passwordBy=By.id("password");
private By loginbutton=By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]");

public void loginPage(String username,String password){
	driver.findElement(signin).click();
	driver.findElement(usernameBy).sendKeys(username);
	driver.findElement(passwordBy).sendKeys(password);
	driver.findElement(loginbutton).click();
	
}
	
}
