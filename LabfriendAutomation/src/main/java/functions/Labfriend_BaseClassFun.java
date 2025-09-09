package functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import contexts.Labfriend_LoginContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LabFriend_LoginPage;

public class Labfriend_BaseClassFun {
	public static WebDriver driver = null;
	public LabFriend_LoginPage labfriendLoginPage = null;
	public static ExtentReports extent = null;
	public static FileInputStream fis = null;

	public static WebDriver initilizeDriver() throws IOException {

		Properties prop = new Properties();

		String osName = System.getProperty("os.name");

		if (osName.equalsIgnoreCase("Linux")) {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/data/LabFriendGlobalData/GlobalData.properties");
		} else {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\data\\LabFriendGlobalData\\GlobalData.properties");
		}

		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			if (osName.equalsIgnoreCase("Linux")) {
				System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

				// Configure Chrome options to run in headless mode
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-gpu"); // Applicable for headless mode
				options.addArguments("window-size=1920x1080");

				// Initialize ChromeDriver with options
				driver = new ChromeDriver(options);
			} else {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-gpu"); // Applicable for headless mode
				options.addArguments("window-size=1920x1080");
				driver = new ChromeDriver();
			}

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edgebrowser")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.manage().window().maximize();

		return driver;

	}

	public static ExtentReports reportConfig() {

		String path = null;
		ExtentSparkReporter reporter = null;
		String osName = System.getProperty("os.name");
		if (osName.equalsIgnoreCase("Linux")) {
			path = System.getProperty("user.dir") + "/reports/report.html";

		} else {
			path = System.getProperty("user.dir") + "\\reports\\report.html";
		}

		reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("LabFriend Production Regression Test");
		reporter.config().setDocumentTitle("Document Title");
		extent = new ExtentReports();
		if (osName.equalsIgnoreCase("Linux")) {
			extent.setSystemInfo("OS", "Linux-24-04");
			extent.setSystemInfo("Browser", "Chrome");
			extent.setSystemInfo("Environment", "Labfriend Production");

		} else {
			extent.setSystemInfo("OS", "Windows");
			extent.setSystemInfo("Browser", "Chrome");
			extent.setSystemInfo("Environment", "Labfriend Production");
		}

		extent.attachReporter(reporter);

		return extent;
	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String osName = System.getProperty("os.name");
		File file = null;
		String systemPropertyOfImage = null;

		if (osName.equalsIgnoreCase("Linux")) {
			systemPropertyOfImage = System.getProperty("user.dir") + "/reports/" + testcaseName + ".png";
			file = new File(systemPropertyOfImage);
		} else {
			systemPropertyOfImage = System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png";
			file = new File(systemPropertyOfImage);
		}

		FileUtils.copyFile(source, file);

		return systemPropertyOfImage;
	}

	public static void implicitGlobalWait(int waitTimeInSeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimeInSeconds));
	}

	@BeforeMethod
	public void launchApplication() throws IOException {
		// Before Method is executed before each test is executed.
		Labfriend_LoginContext loginContext = new Labfriend_LoginContext();
		driver = initilizeDriver();
		labfriendLoginPage = new LabFriend_LoginPage(driver);
		labfriendLoginPage.gotoHomePage(driver); //Load Home Page 
		labfriendLoginPage.loginApplication(loginContext.getemail(), loginContext.getpassword());

	}

	@AfterMethod
	public void tearDown() {
		// After Method is executed after each test is executed completely.
		driver.quit();
		extent.flush();
	}
}
