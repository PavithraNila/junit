package org.flipkart;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.BeforeClass;
import org.junit.Ignore;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class MobileValidation{
	static WebDriver driver;
	static long startTime;
	static String name1;
	@BeforeClass
	public static void BeforeClass() {
		  WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
		  
		  driver.get("https://www.flipkart.com/");
		  driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@AfterClass
	public  static void AfterClass() {
	 driver.quit();
		
	}
	@Before
	public void BeforeTest() {
		startTime=System.currentTimeMillis();
	}
	@After
	public void AfterTest() {
		long endTime=System.currentTimeMillis();
		System.out.println("Time is Taken:" + (endTime - startTime));
	}
	
	@Test
	public void test1() {
		try {
			WebElement close=driver.findElement(By.xpath("//button[text()='✕']"));
			close.click();
		}
		catch(Exception e){
			//handle exception
		}
	}
	@Test
	public void test2() {
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("realme",Keys.ENTER);
	}
	@Test
	public void test3() {
		WebElement mobileName1=driver.findElement(By.xpath("(//div[contains(text(),'realme')])[2]"));
		name1=mobileName1.getText();
		System.out.println(name1);
	}
	@Test
	public void test4() {
		String parent= driver.getWindowHandle();
		Set<String> children=driver.getWindowHandles();
		for(String x:children) {
			if(!parent.equals(x)) {
				driver.switchTo().window(x);
			}
		}
	}
	@Test
	public void test5() {
		WebElement mobileName2=driver.findElement(By.xpath("//div[text()='₹7,499']"));
		String name2=mobileName2.getText();
		System.out.println(name2);
		
		Assert.assertTrue(mobileName2.isDisplayed());
		Assert.assertEquals(name1,name2);
	}
	
}
