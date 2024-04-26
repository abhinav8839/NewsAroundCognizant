package PageObjects;
 
import java.util.List;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.PageFactory;
 
public class SecondPage {

	public WebDriver driver;
	   //constructor
	   public SecondPage(WebDriver driver){
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
	   }

	   By SecondPageNews = By.xpath("//*[@id='news_text_title']");
	   public List<WebElement> SecondNews(){
		   return driver.findElements(SecondPageNews);

	   }

}
