import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class StepStoneTest {

	static WebDriver driver;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, InterruptedException {


		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "//lib//chromedriver.exe");
		driver = new ChromeDriver();

		 WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//Launch URL
		driver.get("https://www.cwjobs.co.uk/");
		driver.manage().window().maximize();
		
		//click on more options
		WebElement btnMore = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='More options']")));
		btnMore.click();

		//click on jobtype
		WebElement btnJobType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='JobType']")));
		btnJobType.click();

		//SelectJobTypr

		WebElement dropdown = driver.findElement(By.xpath("//select[@id='JobType']"));  
		Select select = new Select(dropdown);  
		List<WebElement> options = select.getOptions();  

		for(WebElement we:options)  
		{
			boolean match = false;
			if(we.getText().equalsIgnoreCase("Permanent"))
			{
				match=true;
				Assert.assertTrue(match);
				break;
			}

		}

		//2.	Submit a search for permanent jobs with recruiter type of “Employer” in Manchester,
		// with a salary of at least £20000 for the keyword “manager”

		//Recruiter Type:  Employer
		
		WebElement btnEmployee = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@id='recruiterTypeButtonEmployer']")));
		btnEmployee.click();
		
		//salary type 

		WebElement btnSalaryTypeAnual=driver.findElement(By.xpath("//label[@id='salaryButtonAnnual']"));
		btnSalaryTypeAnual.click();

		//select salary
		WebElement btnSalaryType =driver.findElement(By.xpath("//select[@id='salaryRate']"));
		btnSalaryType.click();
		
		Select oSelect = new Select(driver.findElement(By.xpath("//select[@id='salaryRate']")));  

		oSelect.selectByValue("20000");

		//job title
		WebElement txtjobTitle=driver.findElement(By.xpath("//input[@id='keywords']"));
		txtjobTitle.sendKeys("manager");
		
		//where
		WebElement txtLoc = driver.findElement(By.xpath("//input[@id='location']"));
		txtLoc.sendKeys("Manchester");
		
		//click on search
		WebElement btnSearch=driver.findElement(By.xpath("//input[@type='submit']"));
		btnSearch.click();

		//verify search results  links 
		List<WebElement> resultEntries = driver.findElements(By.xpath("//a/h2[1]"));

		for(WebElement element : resultEntries){
			System.out.println(element.getText());
			Assert.assertTrue(( resultEntries.size()!=0));
			String linkname = null; 
			Boolean contains = driver.getPageSource().contains("APPMEASUREMENT CODE");
			System.out.println(contains);


		}

		//close the browser
		driver.quit();


	}
}
