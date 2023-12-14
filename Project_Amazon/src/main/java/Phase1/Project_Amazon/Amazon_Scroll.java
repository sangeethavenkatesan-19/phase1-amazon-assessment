package Phase1.Project_Amazon;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
/*
 * * Author Sangeetha Venkatesan
 */
public class Amazon_Scroll {

		static WebDriver driver;

		public static void main(String[] args) {

			setUp();

			mobileLinkTest();
			
		}

		public static void setUp() {

			// step1: formulate a test domain url & driver path
			String siteUrl = "https://www.amazon.in";
			String driverPath = "drivers/windows/chromedriver.exe";

			// step2: set system properties for selenium dirver
			System.setProperty("webdriver.chrome.driver", driverPath);

			// step3: instantiate selenium webdriver
			driver = new ChromeDriver();

			// step4: add implicit wait (Unconditional Delay)
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

			// step5: launch browser
			driver.get(siteUrl);
		}

		// Relative Xpath
		public static void mobileLinkTest() {

			WebElement mobileLink = driver.findElement(By.xpath("//*[@id=\"nav-xshop\"]/a[6]"));

			System.out.println("Is link loaded :: " + mobileLink.isDisplayed());
			System.out.println("Is link is enabled :: " + mobileLink.isEnabled());

			mobileLink.click();

			String expectedTitle = "Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
			String actualTitle = driver.getTitle();

			if (expectedTitle.equals(actualTitle)) {
				System.out.println("Test is Passed !");
			} else {
				System.out.println("Test is Failed !");
			}
			takeScreenShot("amazonscroll1.png");
			System.out.println("Expected Title : " + expectedTitle);
			System.out.println("Actual Title : " + actualTitle);
			
			JavascriptExecutor js = (JavascriptExecutor) driver;

	        // Scroll down by a certain pixel amount
	        js.executeScript("window.scrollBy(0, 9000)");
	        takeScreenShot("amazonscroll2.png");
		}
		public static void takeScreenShot(String fileName) {
			// 1. type cast driver instance too take screen shot
			TakesScreenshot tsc = (TakesScreenshot) driver ;
			
			// 2. call take screen shot method with file type
			File src = tsc.getScreenshotAs(OutputType.FILE);
			
			// 3. create  file  with screen shot
			try {
				FileHandler.copy(src, new File("screenshot-output\\"+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//xpath: //*[@id="nav-signin-tooltip"]/a/span
		}
	}