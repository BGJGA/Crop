package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
public class AddToCart {
	WebDriver driver;
	public AddToCart(WebDriver driver) {
		this.driver=driver;
	}

	private By signin=By.xpath("//div[@class=\"app-bar_text__HwUaf\"]");
	private By emailBy=By.id("userData");
	private By passwordBy=By.id("password");
	private By loginbutton=By.xpath("//button[@type=\"submit\" and text()=\"LOGIN\"]");
	private By searchbar=By.cssSelector("input.text-black");
	private By searchbutton=By.cssSelector("button.app-bar_search_desktop__BRcZg");
	private By useraccount=By.cssSelector("div.app-bar_text__HwUaf");
	


	public void addtocart(String email,String password,String product) {
		
		driver.findElement(signin).click();
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(loginbutton).click();
		driver.findElement(searchbar).sendKeys(product);
		driver.findElement(searchbutton).click();
}}
