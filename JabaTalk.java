/*
 One of the travel websites requires you to automate their Flight Search function. Automation Requirements:
1 Launch a new Browser.
2 Open URL http://jt-dev.azurewebsites.net/#/SignUp
3 Validate that the dropdown has "English" and "Dutch"
4 Fill in your name.
5 For organization, use your name as well.
6 Input your email address.
7 Click on "I agree to the Terms And Conditions"
8 Click on "SignUp"
9 Validate that you received an email.
 */


package Jabatalks;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Test
public class JabaTalk {
	WebDriver driver;
	SoftAssert softassert = new SoftAssert();
	public void SignupForm() throws InterruptedException, IOException { 
	
		//1. Launch a new Browser.
		
		System.setProperty("webdriver.chrome.driver","D:\\Ritika\\Ritika_JabaTalks\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		// 2 Open URL http://jt-dev.azurewebsites.net/#/SignUp
		
		driver.navigate().to("http://jt-dev.azurewebsites.net/#/SignUp");
		String expectedURL = "http://jt-dev.azurewebsites.net/#/SignUp";
		String originalURL = driver.getCurrentUrl(); 
		softassert.assertEquals(originalURL, expectedURL);
		
		// import properties file
		
		Properties prop= new Properties();
		   FileInputStream ft;
		   ft= new FileInputStream("D:\\Ritika\\Ritika_JabaTalks\\JabaTalks\\src\\Property\\application.properties");
		   prop.load(ft);
		
		Thread.sleep(5000);
		
		//3 Validate that the dropdown has "English" and "Dutch"
		
		driver.findElement(By.xpath(prop.getProperty("Dropdown"))).click();
	 	List <WebElement> Options = driver.findElements(By.xpath(prop.getProperty("DropdownList")));
		
		for(WebElement option:Options)  
	 	{  
	  	if(option.getText().equalsIgnoreCase("English")) {
	  		option.click();		
	  	}
	  	else if(option.getText().equalsIgnoreCase("Dutch")){
	  		option.click();
	  	}
	  	else {
	  		System.out.println("Language not found");
	  		}
	 	}  
		
		/*
		  4 Fill in your name.
		  5 For organization, use your name as well.
          6 Input your email address.
		 */
		
		driver.findElement(By.id("name")).sendKeys("Ritika Jain");
		driver.findElement(By.id("orgName")).sendKeys("Ritika Jain21");
		driver.findElement(By.id("singUpEmail")).sendKeys("ritikajain.2108@gmail.com");
		
		Thread.sleep(2000);
		/*
		 7 Click on "I agree to the Terms And Conditions"
		 8 Click on "SignUp"
		 */
				
		driver.findElement(By.xpath(prop.getProperty("T&C"))).click();
		driver.findElement(By.xpath(prop.getProperty("Started"))).click();
		
		//9 Validate that you received an email.
		
		Thread.sleep(3000);
		
		if(driver.findElement(By.xpath("//span[contains(text(),'A welcome email has been sent. Please check your email')]")).isDisplayed())
		{
			 System.out.println("Email sent is verified");  
		}
		else{
			System.out.println("Email verification has been failed"); 
			}
	}
}
