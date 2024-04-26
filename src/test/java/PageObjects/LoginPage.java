package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 public class LoginPage {
   public WebDriver driver;

   //constructor
   public LoginPage(WebDriver driver){
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }

   @FindBy(name="passwd")
   WebElement Pasw; 
   public void Pasw() {
	   Pasw.sendKeys("******");
   }

   @FindBy(id="idSIButton9")
   WebElement NextBtn;
   public void pressNextButton()

	{
		 NextBtn.click();
	}
}
