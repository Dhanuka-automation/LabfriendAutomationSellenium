package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Labfriend_CheckoutProductsPage {

	public WebDriver driver;
	private static WebElement element = null;

	public Labfriend_CheckoutProductsPage(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement cartSymbol() {
		// Click mini cart symbol button
		element = driver.findElement(By.xpath("//a[@data-cy='cart-icon-button-container']"));
		return element;
	}

	public WebElement searchField() {
		// Click search field

		element = driver.findElement(By.xpath("//input[@name='q'][1]"));
		return element;
	}

	public WebElement searchItem() {
		// Click on searched item
		element = driver.findElement(By.xpath("//li[contains(text(),'Part #: ')]"));
		return element;
	}

	public WebElement searchResultImage() {

		// Click on searched item
		element = driver.findElement(By.xpath(
				"//h3[@class='ss__result-title text-md md:text-lg product-card__title font-400 mb-[4px] line-clamp-3']"));
		return element;

	}

	public WebElement addToCartButton() {
		// Click on addToCartButton
		element = driver.findElement(By.xpath("//button[@data-cy='add-to-cart-button']"));
		return element;

	}

	public WebElement checkoutButton() {
		// Click on checkoutButton
		element = driver.findElement(By.xpath("//div[text()='Checkout']"));
		return element;
	}

	public WebElement estimatedShipping() {
		//Click estimatedShippingButton
		element = driver.findElement(By.xpath("//span[@class='text-md font-500 text-B-500 hover:text-B-400']"));
		return element;
	
	}
	
	public WebElement clickCalculate() {
		//Click calculate button
		element = driver.findElement(By.xpath("//button[@data-cy='btn-calculate-shipping']"));
		return element;
	
	}
	
	
}
