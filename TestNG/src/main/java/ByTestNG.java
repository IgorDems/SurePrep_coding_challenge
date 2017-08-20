package TestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

/**
 * 
 * @author igor
 *
 */
public class ByTestNG {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "/Users/igor/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://sureprep-frontend-coding-challenge.s3-website-us-east-1.amazonaws.com/");
		System.out.println(driver.getCurrentUrl() + " ==== " + driver.getTitle() + "page opened");
	}

	@Test(priority = 1, invocationCount = 1, description = "Search by 'm' outbound destinations and veryfying airport code: JNB, BOM and SYD.")
	public void Test1() {
		System.out.println("Test 1");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/input")))
				.sendKeys("m");

		List<WebElement> destinations = driver.findElements(By.xpath(""

				+ ".//*[@id='root']/div/main/div[@class='destination']"));

		System.out.println(destinations.size());
		for (int i = 0; i < destinations.size(); i++)

		{

			WebElement textfield = destinations.get(i);
			String text = textfield.getText();

			if (text.contains("JNB")) {
				System.out.println("Bonus of verifying outbound airport code: JNB - test is correct");
			} else if (text.contains("BOM")) {
				System.out.println("Bonus of verifying outbound airport code: BOM - test is correct");
			} else if (text.contains("SYD")) {
				System.out.println("Bonus of verifying outbound airport code: SYD - test is correct");
			}
			System.out.println("-----Search by 'm' for result " + (i + 1) + " ----(Full Text)---------------------");
			System.out.println(text);
			System.out.println("----------------------------------------------------------------------");
		}

	}

	@Test(priority = 2, invocationCount = 1, description = "Search by 'm' inbound destinations and veryfying airport code: JNB, BOM and SYD.")
	public void Test2() {
		System.out.println("Test 2");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/nav/div[2]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/input"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/input")))
				.sendKeys("m");

		List<WebElement> destinations = driver.findElements(By.xpath(""

				+ ".//*[@id='root']/div/main/div[@class='destination']"));

		System.out.println(destinations.size());
		for (int i = 0; i < destinations.size(); i++)

		{

			WebElement textfield = destinations.get(i);
			String text = textfield.getText();

			if (text.contains("JNB")) {
				System.out.println("Bonus of verifying inbound airport code: JNB - test is correct");
			} else if (text.contains("BOM")) {
				System.out.println("Bonus of verifying inbound airport code: BOM - test is correct");
			} else if (text.contains("SYD")) {
				System.out.println("Bonus of verifying inbound airport code: SYD - test is correct");
			}
			System.out.println("-----Search by 'm' for result " + (i + 1) + " ----(Full Text)---------------------");
			System.out.println(text);
			System.out.println("----------------------------------------------------------------------");
		}

	}

	@Test(priority = 3, description = "Search 'Lon' and verify the result is should be 'London, UK'")
	public void Test3() {
		System.out.println("Test 3");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/input"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/input")))
				.sendKeys("Lon");
		WebElement london = driver.findElement(By.xpath(".//*[@id='root']/div/main/div[@class='destination']"));
		String textlon = london.getText();

		if (textlon.contains("London, UK")) {
			System.out.println("Verify the result is 'London, UK': - is correct");

		} else {
			System.out.println("Verifying went with error 'London, UK': - is not correct");
			assert false;
		}
	}

	@Test(priority = 4, description = "Verify there are 2 outbound flights and 3 inbound flights")
	public void Test4() throws InterruptedException {
		System.out.println("Test 4");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/nav/div[1]"))).click();

		// Verify there are 2 outbound flights
		List<WebElement> flightsout = driver.findElements(By.xpath(""

				+ ".//*[@id='root']/div/main/div/div//*[@class='route']"));
		Thread.sleep(3000);
		if (flightsout.size() == 2) {
			System.out.println("Verify the result inbound equal '2': - test is correct");

		} else {
			System.out.println("Verify the result not equal '2': " + flightsout.size() + "  - test is not correct");
			assert false;
		}
		// Verify there are 3 inbound flights

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='root']/div/header/nav/div[2]"))).click();

		List<WebElement> flightsin = driver.findElements(By.xpath(""

				+ ".//*[@id='root']/div/main/div/div//*[@class='route']"));
		Thread.sleep(3000);
		if (flightsin.size() == 3) {
			System.out.println("Verify the result inbound equal '3': - test is correct");

		} else {
			System.out.println("Verify the result not equal '3': = " + flightsin.size() + "  - test is not correct");
			assert false;
		}

	}

	@Test(priority = 5, description = "Verifying location (51°28'14.2\"N 0°27'43.0\"W) in google maps")
	public void Test5() throws InterruptedException {
		System.out.println("Test 5");
		System.out.println("Total number of 'iframes' = " + driver.findElements(By.tagName("iframe")).size());
		driver.switchTo().frame(0);

		String textgpsc = driver.findElement(By.xpath("html/body/div[1]/div/div/div[10]/div/div/div/div[1]/div[1]"))
				.getText();
		Thread.sleep(3000);
		System.out.println("Verifying location result = " + textgpsc + "in google maps");

		if (textgpsc.contains("51°28'14.2\"N 0°27'43.0\"W")) {
			System.out.println(
					"Verifying the result location (51°28'14.2\"N 0°27'43.0\"W) in google maps -test result is correct");

		} else {

			System.out.println("Verifying (51°28'14.2\"N 0°27'43.0\"W) went with  error: - test result is not correct");
			Assert.assertEquals(textgpsc.contains("51°28'14.2\"N 0°27'43.0\"W"), false);
		}
	}

	@AfterTest
	public void closeApp() {

		driver.close();
		System.out.println("=========================BOWSER WAS CLOSED - OK!=======================");
	}

}
