package PageObjects;
 
import java.util.List;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
 
public class FirstPage {

	public WebDriver driver;
	
	   //constructor
	   public FirstPage(WebDriver driver){
		   this.driver=driver;
		   PageFactory.initElements(driver, this);
	   }

	   @FindBy(xpath="//button[contains(@title,'Account manager')]")
	   WebElement user;

	   public void ClickUser() {
		   user.click();
	   }

	   @FindBy(xpath="//div[@class=\"mectrl_name mectrl_truncate\"]")
	   WebElement name;

	   public String getUsername() {
		   return name.getText();
	   }

	   @FindBy(xpath="//div[@id=\"mectrl_currentAccount_secondary\"]")
	   WebElement emailId;
	   public String getUserEmail() {
		   return emailId.getText();
	   }

	   @FindBy(xpath="//span[@class=\"fontSizeXLarge\"]/strong")
	   public WebElement AroundCog;
	   public String VerifyAroundCog()
	   {
		   return AroundCog.getText();
	   }
	   

	   By FirstPageNews = By.xpath("//*[@id=\"news_text_title\"]");
	   public List<WebElement> FirstNews(){
		   return driver.findElements(FirstPageNews);
	   }

	   By ToolTips = By.xpath("//*[@class='z_a_91bed31b']//div//div[2]//a");
	   public List<WebElement> ToolTips(){
		   return driver.findElements(ToolTips);
	   }

	   @FindBy(xpath="//strong[normalize-space()='See all']")
	   public WebElement SeeAll;
	   public void ClickSeeAll() {
		   SeeAll.click();
	   }

}