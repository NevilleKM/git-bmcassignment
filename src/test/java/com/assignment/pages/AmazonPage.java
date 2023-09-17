package com.assignment.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.assignment.base.BaseTest;

public class AmazonPage extends BaseTest{
	
	WebDriver driver;
	Logger log = Logger.getLogger(AmazonPage.class);
	
	public AmazonPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//textarea[@type='search']")
	public WebElement googleSearch;
	
	@FindBy(xpath="//textarea[@type='search']/../../../following-sibling::div/descendant::span")
	public WebElement searchOption;
	
	@FindBy(xpath="//span[text()='Hello, sign in']")
	public WebElement signIn;
	
	@FindBy(xpath="//input[@id='ap_email']")
	public WebElement emailField;
	
	@FindBy(xpath="//input[@id='continue']")
	public WebElement continueButton;
	
	@FindBy(xpath="//input[@id='ap_password']")
	public WebElement passwordField;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	public WebElement submitButton;
		
	@FindBy(id="twotabsearchtextbox")
	public WebElement amazonSearch;
	
	@FindBy(xpath="//div[@class='s-suggestion-container']/div")
	public WebElement amazonSearchOption;
	
	@FindBy(id="low-price")
	public WebElement lowField;
	
	@FindBy(id="high-price")
	public WebElement highField;
	
	@FindBy(xpath="//input[@id='high-price']/following-sibling::span/span/input")
	public WebElement GoButton;
	
	@FindBy(xpath="//a[@class='s-pagination-item s-pagination-button']")
	public WebElement paginationOption;
	
	@FindBy(xpath="//span[text()='5.0 out of 5 stars']/../../../../../../../div/h2/a/span")
	public WebElement itemInWishLIst;
	
	@FindBy(xpath="//span[text()='5.0 out of 5 stars']/../../../../../../../div/h2/a")
	public WebElement hyperLinkTest;
	
	@FindBy(xpath="//input[@value='Add to Wish List']")
	public WebElement wishListSubmit;
	
	@FindBy(id="huc-view-your-list-button")
	public WebElement viewYourList;
	
	@FindBy(xpath="//h2[@class='a-size-base']/a")
	public WebElement wishListProduct;
	
	
	public void entersearchData(String city)
	{
		googleSearch.sendKeys(city);
	}
	
	public List<WebElement>getSearchResults(){
		
		List<WebElement> searchResults = driver.findElements(By.xpath("//textarea[@type='search']/../../../following-sibling::div/descendant::span"));
		return searchResults;
	}
	
	public void selectSearchData()
	{
		searchOption.click();
	}
	
	public void clickSignIn()
	{
		signIn.click();
	}
	
	public void enteremailId(String email)
	{
		emailField.sendKeys(email);
	}
	
	public void clickContinue()
	{
		continueButton.click();
	}
	
	public void enterPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void clickSubmit()
	{
		submitButton.click();
	}
	
	public void selectAllCategory() {
		
		Select select = new Select(driver.findElement(By.id("searchDropdownBox")));
		select.selectByVisibleText("All Categories");
	}
	
	public void enterAmazonSearchData(String searchData)
	{
		amazonSearch.sendKeys(searchData);
	}
	
	public void selectAmazonSearchData()
	{
		amazonSearchOption.click();
	}
	
	public void setLowPrce(String lowPrice)
	{
		lowField.sendKeys(lowPrice);
	}
	
	public void setHighPrice(String highPrice)
	{
		highField.sendKeys(highPrice);
	}
	
	public void clickGo()
	{
		GoButton.click();
	}
	
	public void clickPagination()
	{
		paginationOption.click();
	}
	
	public void verifyPriceRange() {
		List<WebElement> priceOfItems = driver.findElements(By.xpath("//span[@class ='a-price-whole']"));
		String barePrice;
		int price;
		for (int i = 0; i < priceOfItems.size(); i++) {
			barePrice = priceOfItems.get(i).getText().replace(",", "");
			price = Integer.parseInt(barePrice);
			if ((price - 30000) > 0 && (50000 - price) > 0) {
				log.info("Price is within range");
			} else {
				log.info("Price is outside range");
			}
		}
	}
	
	
	public void printFiveStartItems() {
		List<WebElement> fiveStarItems=driver.findElements(By.xpath("//span[text()='5.0 out of 5 stars']/../../../../../../../div/h2/a/span"));
		for (int i =0;i<fiveStarItems.size();i++) {
			
			System.out.println(fiveStarItems.get(i).getText());
			
		}
	}
	
	public String getWishList() {
		return itemInWishLIst.getText();
	}
	
	public String getHyperlinkText() {
		return hyperLinkTest.getAttribute("href");
	}
	
	public void addToWishList()
	{
		wishListSubmit.click();
	}
	
	public void viewWishList()
	{
		viewYourList.click();
	}
	
	public String getWishListProduct() {
		return wishListProduct.getText();
	}

}
