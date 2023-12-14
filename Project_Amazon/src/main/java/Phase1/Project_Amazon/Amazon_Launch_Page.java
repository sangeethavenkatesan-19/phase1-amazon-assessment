package Phase1.Project_Amazon;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
/*
 * **Author Sangeetha Venkatesan
 */
public class Amazon_Launch_Page {
		static WebDriver driver = null;
		public static void main(String[] args) {
			//step 1: formulate a test domain url and driver path
			String siteUrl = "https://www.amazon.in/";
			String driverPath = "drivers/windows/chromedriver.exe";
					
			//step 2: set system properties for selenium driver
			System.setProperty("webdriver.chrome.driver", driverPath);
		
			//step 3: Instantiate selenium web driver
			driver = new ChromeDriver();
					
			//step 4: launch browser
			driver.get(siteUrl);
				
			//step 5: Evaluate the test
			String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
			String actualTitle = driver.getTitle();
					
			if(expectedTitle.equals(actualTitle)) {
					System.out.println("Test is Passed");
			}
			else { 
						System.out.println("Test is failed");
			}
			
			takeScreenShot("amazonlaunch.png");
			
			System.out.println("The actual url :: "+actualTitle);
			System.out.println("The expected URl :: "+expectedTitle); 
			
			//step 6: close browser
			driver.close();
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