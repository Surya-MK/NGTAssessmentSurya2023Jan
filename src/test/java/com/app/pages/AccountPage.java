package com.app.pages;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AccountPage {
	WebDriver driver=null;
	public AccountPage(WebDriver driver) {
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
	public void clickProfile() throws Exception {
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS) ;
		WebElement profile = driver.findElement(By.xpath("//span[.='Profile']"));
		Actions action = new Actions(driver);
		action.moveToElement(profile).perform();
		//profile.click();
		driver.findElement(By.xpath("//div[.=' Edit Profile ']")).click();
	}
	public void verifyUserName() {
		String actualusername = driver.findElement(By.xpath("//div[.='Surya Mahendran']")).getText();
		String expectedusername = "Surya Mahendran";
		Assert.assertEquals(actualusername, expectedusername);
	}
}
