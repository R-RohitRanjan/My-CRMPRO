package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.Utility.TestUtil;
import com.Utility.WebEventListener;

public class BasePage implements IAutoConst {
	public static WebDriver driver;
	public static Properties prop;
	
	public  static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;
	static
	{
		System.setProperty(KEY, VALUE);
	}
	public BasePage() {
		prop = new Properties();
		FileInputStream inStream;
		try {
			inStream = new FileInputStream("FreeCRMPRO2020\\src\\main\\java\\com\\config\\initialData.properties");
			prop.load(inStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialization() {

		String browser = prop.getProperty("browserName");
		String appURL = prop.getProperty("url");
		driver = new ChromeDriver();
		if(browser.equals("chrome")) {
			System.setProperty(KEY, VALUE);
		driver = new ChromeDriver();
		}
		else if (browser.equals("firefox")) {
			System.setProperty(KEY, VALUE);
			driver = new FirefoxDriver();
		}
		else if (browser.equals("edge")) {
			System.setProperty(KEY, VALUE);
			driver = new EdgeDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize(); //Maximizing
		driver.manage().deleteAllCookies(); //Deleting the cookies
		driver.get(prop.getProperty("url")); //Enter URL
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		
	}
}
