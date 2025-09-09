package functions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import contexts.Labfriend_CheckoutProducts;
import pages.Labfriend_CheckoutProductsPage;
import pages.Labfriend_CheckoutProductsSingaporePage;

/**
 * @author Dhanuka_Dulanjana
 * 
 */

public class Labfriend_CheckoutProductsSingaporeFun extends LabFriend_CommonFun {
	WebDriver driver;
	String propertyFileName;
	String folderPath;

	public Labfriend_CheckoutProductsSingaporeFun(WebDriver driver, String propertyFileName, String folderPath) {
		super(driver);
		this.driver = driver;
		this.propertyFileName = propertyFileName;
		this.folderPath = folderPath;
	}

	public void mainTest() {

		Labfriend_CheckoutProductsSingaporePage checkoutProductsSingaporePage = new Labfriend_CheckoutProductsSingaporePage(
				driver);
		Labfriend_CheckoutProducts labfriend_checkoutPropductsContext = new Labfriend_CheckoutProducts(propertyFileName,
				folderPath);
		Labfriend_CheckoutProductsPage checkoutProducts = new Labfriend_CheckoutProductsPage(driver);

		Actions a = new Actions(driver);
		// click store drop down
		a.moveToElement(checkoutProductsSingaporePage.clickDropDown()).build().perform();
		waitForElementToAppear(By.xpath("//span[@data-cy='store-dropdown-option-Singapore']"), 5);
		// click singapore
		a.moveToElement(checkoutProductsSingaporePage.clickStore()).click().build().perform();

		// close pop up

		if (iselementPresentInTheDom(driver, By.xpath(
				"//button[@type='button'][@class='btn !bg-[transparent] !p-0 rounded-[4px] text-base font-600 flex items-center justify-center border group ease-in-out duration-100 h-[32px] px-[16px] text-[8px] bg-[transparent] hover:bg-N-50 active:bg-N-100 border-[transparent] btn-surface relative trans-200']"))) {

			a.moveToElement(checkoutProductsSingaporePage.closePopup()).click().build().perform();
		}

		a.moveToElement(checkoutProducts.searchField()).click()
				.sendKeys(labfriend_checkoutPropductsContext.getSingaporeSku()).build().perform();
		// Click on search box and pass a SKU.
		a.moveToElement(checkoutProducts.searchField()).click().keyDown(Keys.ENTER).build().perform();

		waitForElementToAppear(By.xpath(
				"//h3[@class='ss__result-title text-md md:text-lg product-card__title font-400 mb-[4px] line-clamp-3']"),
				10);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		a.moveToElement(checkoutProductsSingaporePage.searchResultSingapore()).click().build().perform(); // Click on
																											// the
																											// searched
																											// product

		waitForElementToAppear(By.xpath("//input[@data-cy='input-undefined']"), 10);// wait until increment button to
																					// appear

		js.executeScript("window.scrollBy(0,500)");

		a.moveToElement(checkoutProductsSingaporePage.incremenSymbol()).click().build().perform();

		waitForElementToAppear(By.xpath("//button[@data-cy='add-to-cart-button']"), 10); // Wait Add to Cart button

		a.moveToElement(checkoutProducts.addToCartButton()).click().build().perform(); // click add to cart button

		checkoutProducts.cartSymbol().click();// click cart button

		checkoutProducts.estimatedShipping().click(); // click estimated shipping

		checkoutProducts.clickCalculate().click(); // click calculate button

		checkoutProducts.checkoutButton().click();// click checkout button

		checkoutProductsSingaporePage.guestCheckout().click();// click guest checkout button

	}
}
