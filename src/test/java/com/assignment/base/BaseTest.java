package com.assignment.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest
{      
static WebDriver driver;
Properties prop = new Properties();

@BeforeSuite
public void setupApplication() throws IOException
{
	 FileInputStream input = new FileInputStream("C:\\Properties\\application.properties");
 	 prop.load(input);
	 
 	if(prop.getProperty("browser").contains("firefox")) {
		 System.setProperty("webdriver.gecko.driver", "C:\\firefoxdriver_win32\\geckodriver.exe");
	     driver=new FirefoxDriver();
	     driver.manage().window().maximize();
	 }
 	
	 if(prop.getProperty("browser").contains("chrome")) {
		 System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
	     driver=new ChromeDriver();
	     driver.manage().window().maximize();
	 }
}
 
@BeforeClass
public WebDriver getdriver(){
      return driver;
  }
}