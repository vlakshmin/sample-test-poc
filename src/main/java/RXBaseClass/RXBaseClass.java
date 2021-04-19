package RXBaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cucumber.api.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import RXUtitities.RXUtile;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RXBaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	//public static EventListener eventListener;

	
	public RXBaseClass()
	{


		try {
			String propFile = "src/main/java/RXConfig/RX.properties";
			FileInputStream pFile=new FileInputStream(propFile);
			prop = new Properties();
			prop.load(pFile);
			pFile.close();

			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void driverInitialize()
	{

		String browserName=prop.getProperty("browser");
		
		switch(browserName)
		{
		case "chrome" :
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driverFolder/chromedriver");
			 WebDriverManager.chromedriver().setup();
			 
			 driver = new ChromeDriver();
			 
			 break;
		
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
//		eventListener = new EventListener();
//		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(RXUtile.PAGELOAD_TIME,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(RXUtile.IMPLI_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	
	
	public boolean waitForJSandJQueryToLoad() {

	    WebDriverWait wait = new WebDriverWait(driver, 30);

	    // wait for jQuery to load
	    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver2) {
	        try {
	          return ((Long)((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
	        }
	        catch (Exception e) {
	          // no jQuery present
	          return true;
	        }
	      }
	    };

	    // wait for Javascript to load
	    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver2) {
	        return ((JavascriptExecutor) driver).executeScript("return document.readyState")
	        .toString().equals("complete");
	      }
	    };

	  return wait.until(jQueryLoad) && wait.until(jsLoad);
	}
	public static Stream<Map.Entry<String, String>> getDataFromTable (DataTable dt) {
		return dt.asMaps(String.class, String.class).stream()
				.flatMap(m -> m.entrySet().stream());
	}
	public static Map<String,String> createCopyOfCucumberMap(Map<String,String> map) {
		LinkedHashMap<String,String> copy = map.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return copy;
	}
	public static Map<String,String> modifyMapByBase(Map<String,String> map, String base, int length) {
		// Create a copy of map from Cucumber data table
		LinkedHashMap<String,String> copy = (LinkedHashMap<String,String>) createCopyOfCucumberMap(map);
		// Replace values with random characters generated from base input
		copy.replaceAll((k, v) -> RXUtile.getRandomValue(base,length));
		return copy;
	}
	public static WebDriverWait driverWait() {
		return new WebDriverWait(driver, 60);
	}
}
