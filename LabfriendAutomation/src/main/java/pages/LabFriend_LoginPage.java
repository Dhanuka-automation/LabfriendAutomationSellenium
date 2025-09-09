package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import contexts.Labfriend_LoginContext;
import functions.LabFriend_CommonFun;

public class LabFriend_LoginPage extends LabFriend_CommonFun {
	WebDriver driver;

	public LabFriend_LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void loginApplication(String email, String password) {

		// Using Page Factory method
//		@FindBy(xpath = "//a[@data-cy='signin-link']")
//		WebElement lnkSignIn;

		waitForElementToAppear(By.xpath("//a[@data-cy='signin-link']"),5);
		WebElement lnkSignIn = driver.findElement(By.xpath("//a[@data-cy='signin-link']"));
		lnkSignIn.click();
		WebElement txtEmail = driver.findElement(By.xpath("//input[@name='userName']"));
		WebElement txtPassword = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement btnSignIn = driver.findElement(By.xpath("//button[@data-cy='signin-button']"));

		txtEmail.sendKeys(email);
		txtPassword.sendKeys(password);
		btnSignIn.click();

	}

	public void gotoHomePage(WebDriver driver) {
		driver.get("https://www.labfriend.com.au/");

	}

}
