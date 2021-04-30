package newsNSW;//created a package

//importing the java utility
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import org.junit.Assert; 


public class newsNSW {

	public static void main(String[] args) throws InterruptedException {
		
			
		WebDriver driver=new ChromeDriver();
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");//declaring path for chrome webdriver
		
		
		//Step1: Goto URL-https://www.nsw.gov.au/news
		driver.get("https://www.nsw.gov.au/news"); // opens the url
		driver.manage().window().maximize(); //maximize browser
		Thread.sleep(10000);
		
		
		//Step2: Verify the title of the URL whether on the correct page or not
		System.out.println("Page title is : " + driver.getTitle());
		String expected ="News | NSW Government";
		Assert.assertEquals(driver.getTitle(),expected );
		System.out.println("Assertion Passed successfully"); // Title is verified
		
		
		//Step3: Wait for the 20sec for the web elements to get loaded on the web page 
		driver.manage().timeouts().implicitlyWait((20),TimeUnit.SECONDS);
			
		
		//Step4: Scroll page to location
		WebElement element = driver.findElement(By.xpath("//input[@id=\"edit-submit-news\"]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		
		
		//Step5: Click on the Select topics accordion
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);// Wait for element before throwing an exception 
		driver.findElement(By.cssSelector("#edit-fieldset-category > h2 > button")).click(); // Clicking the Select topics accordion
		System.out.println(" Clicked on Select topics accordion"); 
	
		
		//Step6: Select Business and Economy from Select topics accordion
		WebElement element1 = driver.findElement(By.xpath("//label[contains(.,'Business and Economy')]"));
		element1.click(); //Selecting Business and Economy from Select topics accordion
				
		
		//Step7: Wait for the web element to be loaded and then scroll down the page
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);// Wait for element before throwing an exception 
		WebElement element2 = driver.findElement(By.xpath("//input[@id=\"edit-submit-news\"]"));
		Actions actions1 = new Actions(driver);
		actions1.moveToElement(element2);
		actions1.perform();
	
		
		//Step8:Click on the Apply button
		Thread.sleep(15000); //wait for page elements to get loaded
		driver.findElement(By.xpath("//input[@id=\"edit-submit-news\"]")).click(); //click on the Apply button
		System.out.println("Click on the Apply button");
	
		
		//Step9:Verify filter is applied successfully
		Thread.sleep(5000); //wait for page elements to get loaded
		if(driver.findElement(By.xpath("//p[contains(.,'Business and Economy')]"))!= null)
		{
			System.out.println("Element is Present");
			System.out.println("Business and Economy filter applied successfully");
		}
		else
		{
			System.out.println("Element is Absent");
			System.out.println("Applying Business and Economy filter failed");
		}
		
		
		//Step10:Click on the Reset button
		WebElement element3 = driver.findElement(By.xpath("//input[@name=\"op\"]")); //scroll page to reset button
		Actions actions2 = new Actions(driver);
		actions2.moveToElement(element3);
		actions2.perform();
		
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// Wait for element till 10sec before throwing an exception 
	    element3.click(); //click on the Reset button
	 
	    
	    //Step11:Verify clicking on reset button removes applied filters
	    Thread.sleep(5000); //wait for page elements to get loaded
	     Assert.assertNull("element3");//The above assertion will pass if element is not present i.e no reset button is displayed on screen
		 System.out.println("Assertion Passed successfully");
		 System.out.println("The applied filter on the news items is removed ");
		 
		 if(driver.findElement(By.xpath("//p[contains(.,'Business and Economy')]"))== null)
			{
				System.out.println("Element is not Present, filter removed successfully");
				
			}
		 
		 if(driver.findElement(By.xpath("//p[contains(.,'Arts & Culture')]"))!= null)
			{
				System.out.println("Art and Culture is Present, filter is removed successfully");
				
			}	
		 
		 
		 //Step12: Close the browser instance
         driver.quit();
	}
	
	 
	  	 
	  
	
	}


