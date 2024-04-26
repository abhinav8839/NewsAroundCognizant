 package casproject;
 
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
 
public class ScrollToElement {
	JavascriptExecutor js;
	WebDriver driver;
	ScrollToElement(WebDriver driver)
	{
		this.driver=driver;
	}

	public void Scroll(WebElement ele)
	{
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments [0].scrollIntoView();",ele);
	} 
}