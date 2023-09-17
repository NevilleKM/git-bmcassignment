package com.assignment.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UIWithoutFramworkTest {
	
	public static void main(String[] args) throws InterruptedException {
	
	System.setProperty("webdriver.gecko.driver", "C:\\firefoxdriver_win32\\geckodriver.exe");
	WebDriver driver;
	driver = new FirefoxDriver();
	driver.get("http://www.google.com");
	
	driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("amazon");
	Thread.sleep(5000);	
	List<WebElement> searchResults=driver.findElements(By.xpath("//textarea[@type='search']/../../../following-sibling::div/descendant::span"));
	
	for (int i =0;i<searchResults.size();i++) {
		if(searchResults.get(i).getText()!="") {
		System.out.println(searchResults.get(i).getText());
		}
	}
	driver.findElement(By.xpath("//textarea[@type='search']/../../../following-sibling::div/descendant::span")).click();
	driver.get("https://www.amazon.in/");
	Thread.sleep(5000);	
	driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();
	Thread.sleep(5000);	
	driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys("kiritneville@gmail.com");
	driver.findElement(By.xpath("//input[@id='continue']")).click();
	driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys("EastSudan@123");
	driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	
	Select select = new Select(driver.findElement(By.id("searchDropdownBox")));
	select.selectByVisibleText("All Categories");
	
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys("dell computers");
	Thread.sleep(5000);	
	driver.findElement(By.xpath("//div[@class='s-suggestion-container']/div")).click();
	
	driver.findElement(By.id("low-price")).sendKeys("30000");
	driver.findElement(By.id("high-price")).sendKeys("50000");
	driver.findElement(By.xpath("//input[@id='high-price']/following-sibling::span/span/input")).click();
	
	List<WebElement> priceOfItem=driver.findElements(By.xpath("//span[@class ='a-price-whole']"));
	String barePrice;
	int price;
	for (int i =0;i<priceOfItem.size();i++) {
		barePrice=priceOfItem.get(i).getText().replace(",", "");
		price = Integer.parseInt(barePrice);
		if((price-30000)>0 && (50000-price)>0) {
			System.out.println("Price is within range");
		}else {
			System.out.println("Price is outside range");
		}
	}
	driver.findElement(By.xpath("//a[@class='s-pagination-item s-pagination-button']")).click();
	priceOfItem=driver.findElements(By.xpath("/span[@class ='a-price-whole']"));
	for (int i =0;i<priceOfItem.size();i++) {
		barePrice=priceOfItem.get(i).getText().replace(",", "");
		price = Integer.parseInt(barePrice);
		if((price-30000)>0 && (50000-price)>0) {
			System.out.println("Price is within range");
		}else {
			System.out.println("Price is outside range");
		}
	}
	Thread.sleep(5000);
	driver.findElement(By.xpath("//a[@class='s-pagination-item s-pagination-button']")).click();
	Thread.sleep(5000);	
	List<WebElement> fiveStarItems=driver.findElements(By.xpath("//span[text()='5.0 out of 5 stars']/../../../../../../../div/h2/a/span"));
	for (int i =0;i<fiveStarItems.size();i++) {
		
		System.out.println(fiveStarItems.get(i).getText());
		
	}
	driver.findElement(By.xpath("//a[@class='s-pagination-item s-pagination-button']")).click();
	Thread.sleep(5000);	
	fiveStarItems=driver.findElements(By.xpath("//span[text()='5.0 out of 5 stars']/../../../../../../../div/h2/a/span"));
	for (int i =0;i<fiveStarItems.size();i++) {
		
		System.out.println(fiveStarItems.get(i).getText());
		
	}
	driver.findElement(By.xpath("//a[@class='s-pagination-item s-pagination-button']")).click();
	Thread.sleep(5000);	
	String itemInWishList=driver.findElement(By.xpath("//span[text()='5.0 out of 5 stars']/../../../../../../../div/h2/a/span")).getText();
	String hyperlinkText=driver.findElement(By.xpath("//span[text()='5.0 out of 5 stars']/../../../../../../../div/h2/a")).getAttribute("href");
	driver.get(hyperlinkText);
	
	driver.findElement(By.id("add-to-wishlist-button-submit")).click();
	Thread.sleep(5000);	
	driver.findElement(By.id("huc-view-your-list-button")).click();
	
	String itemToCompare=driver.findElement(By.xpath("//h2[@class='a-size-base']/a")).getText();
	
	Assert.assertEquals(itemInWishList, itemToCompare);
	
	}

}
