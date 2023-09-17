package com.assignment.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.assignment.base.BaseTest;
import com.assignment.pages.AmazonPage;

public class UIFrameworkTest extends BaseTest {

	WebDriver driver;
	Logger log = Logger.getLogger(UIFrameworkTest.class);
	AmazonPage amazonPage;
	Properties prop = new Properties();

	@BeforeClass
	public void setUp() throws IOException {

		FileInputStream input = new FileInputStream("C:\\Properties\\application.properties");
		prop.load(input);
		getdriver().get(prop.getProperty("url"));
		amazonPage = new AmazonPage(getdriver());

	}

	@Test
	public void testAmazonFunctionality() throws InterruptedException {

		amazonPage.entersearchData("amazon");

		List<WebElement> searchResults = amazonPage.getSearchResults();

		for (int i = 0; i < searchResults.size(); i++) {
			if (searchResults.get(i).getText() != "") {
				log.info(searchResults.get(i).getText());
			}
		}
		
		amazonPage.selectSearchData();

		getdriver().get(prop.getProperty("websiteUrl"));
		Thread.sleep(5000);

		amazonPage.clickSignIn();

		amazonPage.enteremailId(prop.getProperty("emailID"));

		amazonPage.clickContinue();

		amazonPage.enterPassword(prop.getProperty("password"));

		amazonPage.clickSubmit();

		amazonPage.selectAllCategory();

		amazonPage.enterAmazonSearchData("dell computers");
		Thread.sleep(5000);

		amazonPage.selectAmazonSearchData();

		amazonPage.setLowPrce("30000");

		amazonPage.setHighPrice("50000");

		amazonPage.clickGo();

		amazonPage.verifyPriceRange();
		
		amazonPage.clickPagination();
		
		amazonPage.verifyPriceRange();
		
		Thread.sleep(5000);
		amazonPage.clickPagination();
		Thread.sleep(5000);
		amazonPage.printFiveStartItems();
		
		amazonPage.clickPagination();
		Thread.sleep(5000);
		amazonPage.printFiveStartItems();
		
		amazonPage.clickPagination();
		Thread.sleep(5000);	
		String itemInWishList=amazonPage.getWishList();
		String hyperlinkText =amazonPage.getHyperlinkText();
		getdriver().get(hyperlinkText);
		
		amazonPage.addToWishList();
		Thread.sleep(5000);	
		amazonPage.viewWishList();
		
		String itemToCompare=amazonPage.getWishListProduct();
		
		Assert.assertEquals(itemInWishList, itemToCompare);
		
		
		

	}

}
