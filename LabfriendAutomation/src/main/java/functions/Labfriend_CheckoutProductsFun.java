package functions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import contexts.Labfriend_CheckoutProducts;
import pages.Labfriend_CheckoutProductsPage;

public class Labfriend_CheckoutProductsFun extends LabFriend_CommonFun {

	WebDriver driver;
	String propertyFileName;
	String folderPath;

	public Labfriend_CheckoutProductsFun(WebDriver driver, String propertyFileName, String folderPath) {
		super(driver);
		this.driver = driver;
		this.propertyFileName = propertyFileName;
		this.folderPath = folderPath;
	}

	public void mainTest() {

		Labfriend_CheckoutProductsPage checkoutProductsPage = new Labfriend_CheckoutProductsPage(driver);
		Labfriend_CheckoutProducts labfriend_checkoutPropductsContext = new Labfriend_CheckoutProducts(propertyFileName,
				folderPath);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// checkoutProductsPage.cartSymbol().click();

		Actions action = new Actions(driver);
		action.moveToElement(checkoutProductsPage.searchField()).click()
				.sendKeys(labfriend_checkoutPropductsContext.getSku()).build().perform();
		// Click on search box and pass a SKU.
		action.moveToElement(checkoutProductsPage.searchField()).click().keyDown(Keys.ENTER).build().perform();
		// Click on ENTER button.
		waitForElementToAppear(By.xpath(
				"//h3[@class='ss__result-title text-md md:text-lg product-card__title font-400 mb-[4px] line-clamp-3']"),
				10);
		checkoutProductsPage.searchResultImage().click(); // Click on Searched Result

		waitForElementToAppear(By.xpath("//button[@data-cy='add-to-cart-button']"), 5); // Wait Add to Cart button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll down by a specified pixel value (e.g., 1000 pixels)
		js.executeScript("window.scrollBy(0,400)");

		action.moveToElement(checkoutProductsPage.addToCartButton()).click().build().perform();
		// Click on Add To Cart Button
		checkoutProductsPage.cartSymbol().click(); // Click on Cart Button
		
		checkoutProductsPage.estimatedShipping().click(); //click estimated shipping
			
		checkoutProductsPage.clickCalculate().click();//click calculate button

		//checkoutProductsPage.checkoutButton().click(); // Click on Checkout Button
	}

}
