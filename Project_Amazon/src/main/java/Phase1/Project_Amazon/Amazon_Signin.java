package Phase1.Project_Amazon;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Amazon_Signin {

		static WebDriver driver;
		static WebDriverWait wait;

		public static void main(String[] args) throws InterruptedException {

			setUp();

			testFailureLogin();
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
	public static void testFailureLogin() throws InterruptedException {
			
		WebElement signInLink = driver.findElement(By.id("nav-link-accountList"));
    	signInLink.click();

    	takeScreenShot("signinpage.png");
    	
    // Locate the email/phone number field and enter your email/phone
    	WebElement emailField = driver.findElement(By.id("ap_email"));
    	emailField.sendKeys("sangeethahemavenkat@gmail.com");

    	takeScreenShot("emailpage.png");
    	
    // Locate and click the Continue button
    	WebElement continueButton = driver.findElement(By.id("continue"));
    	continueButton.click();

    // Locate the password field and enter your password
    	WebElement passwordField = driver.findElement(By.id("ap_password"));
    	passwordField.sendKeys("abcd1234");

    	takeScreenShot("passwordpage.png");
    	
    // Locate and click the Sign In button
    	WebElement signInButton = driver.findElement(By.id("signInSubmit"));
    	signInButton.click();
    	
    	Thread.sleep(5000);
    	takeScreenShot("loginfail.png");

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