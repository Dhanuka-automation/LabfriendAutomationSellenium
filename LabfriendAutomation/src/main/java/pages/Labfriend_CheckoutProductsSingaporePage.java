package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Dhanuka_Dulanjana
 * 
 */

public class Labfriend_CheckoutProductsSingaporePage {
	public WebDriver driver;
	private static WebElement element = null;

	public Labfriend_CheckoutProductsSingaporePage(WebDriver driver) {
		this.driver = driver;

	}

	public WebElement clickDropDown() {
		// click store dropdown

		element = driver.findElement(By.xpath("//a[contains(@data-cy,'header-store-selected-icon-container')]"));

		return element;
	}

	public WebElement clickStore() {
		// select singapore store

		element = driver.findElement(By.xpath("//span[@data-cy='store-dropdown-option-Singapore']"));

		return element;
	}

	public WebElement closePopup() {
		// close pop up

		element = driver.findElement(By.xpath(
				"//button[@type='button'][@class='btn !bg-[transparent] !p-0 rounded-[4px] text-base font-600 flex items-center justify-center border group ease-in-out duration-100 h-[32px] px-[16px] text-[8px] bg-[transparent] hover:bg-N-50 active:bg-N-100 border-[transparent] btn-surface relative trans-200']"));

		return element;
	}

	public WebElement searchResultSingapore() {

		// Click on searched item
		element = driver.findElement(By.xpath(
				"//li[text()='6.224 148']"));
		return element;

	}
	
	public WebElement incremenSymbol() {
		// Click on increment item
				element = driver.findElement(By.xpath(
						"//button[@data-cy='item-increment']"));
				return element;
	}

	public WebElement guestCheckout() {

		// Click on searched item
		element = driver.findElement(By.xpath("//button[@data-cy='btn-guest-checkout']"));
		return element;

	}

}
