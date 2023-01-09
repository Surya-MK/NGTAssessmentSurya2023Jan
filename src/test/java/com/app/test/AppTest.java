package com.app.test;

import org.testng.annotations.Test;

import com.app.pages.AccountPage;
import com.app.pages.BagMenu;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class AppTest {
	static WebDriver driver = null;
	AccountPage accountpage;
	BagMenu bag;

	@Test
	public void verifyUserName() throws Exception {
		accountpage = new AccountPage(driver);
		accountpage.login();
		accountpage.clickProfile();
		accountpage.verifyUserName();
	}

	@Test
	public void verifyBagMenu() throws Exception {
		bag = new BagMenu(driver);
		//bag.login();
		bag.clickBag();
		bag.addToBag();
		bag.verifyItemNumber();
	}

	@BeforeMethod
	public void setup() throws Exception {
		String rootFolder = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", rootFolder + "//src//test//resources//chromedriver.exe");
		Properties propObj=new Properties();
		propObj.load(new FileInputStream(rootFolder+ "//src//test//resources//application.properties"));
		String url=propObj.getProperty("url");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.get(url);
	}

	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
