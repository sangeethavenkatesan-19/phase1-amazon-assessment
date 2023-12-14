package Phase1.Project_Amazon;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
/*
 * **Author Sangeetha Venkatesan
 */
public class Amazon_Search {
	
		static WebDriver driver = null;

		public static void main(String[] args) throws InterruptedException {

			// step1: formulate a test domain url & driver path
			String siteUrl = "https://www.amazon.in";
			String driverPath = "drivers/windows/geckodriver.exe";

			// step2: set system properties for selenium dirver
			System.setProperty("webdriver.geckodriver.driver", driverPath);

			// step3: instantiate selenium webdriver
			driver = new FirefoxDriver();

			// step4: launch browser
			driver.get(siteUrl);

			testSearch1();
			
		}
		private static void testSearch1() throws InterruptedException {

			WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
			searchBox.sendKeys("Iphone 15 pro max");
			searchBox.submit();

			// add delay 
			Thread.sleep(2000);
			
			String expectedTitle = "Amazon.in : Iphone 15 pro max";
			String actualTitle = driver.getTitle();

			if (expectedTitle.equals(actualTitle)) {
				System.out.println("Test is Passed !");
			} else {
				System.out.println("Test is Failed !");
			}
			
			takeScreenShot("amazoniphonesearch.png");
			
			System.out.println("Expected Title : " + expectedTitle);
			System.out.println("Actual Title : " + actualTitle);
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
		}
	}