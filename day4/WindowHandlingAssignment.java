package week3.day4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandlingAssignment {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        
        //Login Module
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		
		//Click on the widget of the "From Contact". 
		driver.findElement(By.xpath("//img[@src='/images/fieldlookup.gif']")).click();		
		Set<String> childWindow = driver.getWindowHandles();
		List<String> from = new ArrayList<String>(childWindow);
		driver.switchTo().window(from.get(1));
		
	    //Click on the first resulting contact
		driver.findElement(By.linkText("12640")).click();
		
		//Click on the widget of the "To Contact"
		driver.switchTo().window(from.get(0));
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		Set<String> childWindow2 = driver.getWindowHandles();
		List<String> to = new ArrayList<String>(childWindow2);
		driver.switchTo().window(to.get(1));
		
		//Click on the second resulting contact
		driver.findElement(By.linkText("DemoCustomer")).click();
		
		//Click on the Merge button
		driver.switchTo().window(to.get(0));
		driver.findElement(By.linkText("Merge")).click();
		
		//Accept the alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		// Verify the title of the page. 
		String title = driver.getTitle();
		System.out.println(title);
	}

}
