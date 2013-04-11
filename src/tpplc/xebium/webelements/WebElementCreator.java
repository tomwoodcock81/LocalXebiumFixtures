package tpplc.xebium.webelements;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebElementCreator {

	WebDriver driver;
	WebElement element;
	String baseURL;
	String browserDriver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public void setBaseUrl(String url){
		baseURL = url;
	}
	
	public void setDriver(String _driver){
		browserDriver = _driver;
	}
	
	public WebDriver selectDriver(){
		WebDriver theDriver = null;
		if("iexplore".equals(browserDriver)){
			theDriver = new InternetExplorerDriver();
		}
		else if("firefox".equals(browserDriver)){
			theDriver = new FirefoxDriver();
		}
		else if ("chrome".equals(browserDriver)){
			theDriver = new ChromeDriver();
		}
		return theDriver;
	}
	
	public WebElement createElementFromXpath(WebDriver driver, String value) {
		this.driver = selectDriver();
		driver.navigate().to(baseURL + "/");
		WebElement element = driver.findElement(By.xpath(value));
		return element;
	}

	public WebElement createElementFromName(WebDriver driver, String value) {
		this.driver = selectDriver();
		driver.navigate().to(baseURL + "/");
		WebElement element = null;
			driver.findElement(By.name(value));
		return element;
	}

	public WebElement createElementFromId(WebDriver driver, String value) {
		this.driver = selectDriver();
		driver.navigate().to(baseURL + "/");
		WebElement element = null;
			driver.findElement(By.id(value));
		return element;
	}

}
