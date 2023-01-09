package com.app.pages;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BagMenu {
	WebDriver driver=null;
	public BagMenu(WebDriver driver) {
		this.driver=driver;
	}
	public void login() throws Exception {
		String rootFolder = System.getProperty("user.dir");
		Properties propObj=new Properties();
		propObj.load(new FileInputStream(rootFolder+ "//src//test//resources//application.properties"));
		String emailId=propObj.getProperty("emailId");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(emailId);
		String password=propObj.getProperty("password");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[.='LOGIN']")).click();
		Thread.sleep(31000);
		driver.findElement(By.xpath("//button[.='LOGIN']")).click();
	}
	public void clickBag() throws Exception {
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[.='Bag']")).click();
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		driver.findElement(By.xpath("//div[contains(text(),'ADD ITEMS FROM WISHLIST')]")).click();
//		driver.findElement(By.xpath("//a[.='LOGIN']")).click();
//		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("6380042091");
//		driver.findElement(By.xpath("//div[.='CONTINUE']")).click();
//		Thread.sleep(31000);
//		driver.findElement(By.xpath("//div[.='CONTINUE']")).click();
//		driver.findElement(By.xpath("//span[.=' Password ']")).click();
//		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Sur@2000");
//		driver.findElement(By.xpath("//a[.='LOGIN']")).click();
		//driver.navigate().back();
		
		
	}
	public void addToBag() {
		WebElement search=driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"));
		search.click();
		search.sendKeys("Redmi");
		search.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//img[@title='DressBerry Lavender Textured Structured Mobile Pouch']")).click();
		String mainWindowHandle = driver.getWindowHandle();
		String winHandleBefore = driver.getWindowHandle();

		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}

		driver.findElement(By.xpath("//span[@class='myntraweb-sprite pdp-whiteBag sprites-whiteBag pdp-flex pdp-center']")).click();
		driver.findElement(By.xpath("//span[.='GO TO BAG']")).click();
	}
	public void verifyItemNumber() {
		String actualString = driver.findElement(By.xpath("//div[.='1/1 ITEMS SELECTED']")).getText();
		String expectedString = "1/1 ITEMS SELECTED";
		Assert.assertEquals(actualString, expectedString);
		
	}
}
