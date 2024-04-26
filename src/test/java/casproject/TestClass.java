package casproject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import PageObjects.FirstPage;
import PageObjects.LoginPage;
import PageObjects.SecondPage;
import Utilites.ExcelUtility;
import Utilites.Screenshots;
 
 
public class TestClass {

	WebDriver driver;
	JavascriptExecutor js;
	List<String> firstNews=new ArrayList<String>();
	List<String> secondNews=new ArrayList<String>();
	List<String> NEWS = new ArrayList<String>();
	
	public LoginPage Lp;
	ScrollToElement Sr;
	private String i;
	public FirstPage Fp;
	public SecondPage Sp;
	public static String path;

	@BeforeClass
	public void navigate() throws IOException, InterruptedException 
	{
		driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String Url = "https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx";
		driver.get(Url);
		
	}
	
	   
		@Test(priority = 0)
		public void LoginPage() throws IOException
		{
		try {
		driver.manage().window().maximize();
		Lp=new LoginPage(driver);
		Lp.Pasw();
		Lp.pressNextButton();
		Lp.pressNextButton();
		}
		catch (Exception e ){
			System.out.println("Website Opened");
		}
		//ExcelUtiles.excelInit();
		path =Screenshots.takeScreenshot(driver, "Home page");
	}

	@Test(priority=1)
	public void validateUser() throws InterruptedException, IOException 
	{
		Thread.sleep(5000);
		Fp = new FirstPage(driver);
		Fp.ClickUser();
		String Name = Fp.getUsername();
		String Email = Fp.getUserEmail();
		path = Screenshots.takeScreenshot(driver, "USERINFO");
		System.out.println(Name);
		System.out.println(Email);
		Assert.assertEquals(Name,"Jain, Abhinav (Contractor)");
		Assert.assertEquals(Email,"2320204@cognizant.com");
	}

	@Test(priority=2)
	public void validateAroundCognizant() throws IOException
	{
		Sr=new ScrollToElement(driver);
		Sr.Scroll(Fp.AroundCog);
		System.out.println("Scrolled to Around Cognizant");
		String AroundCogn =Fp.VerifyAroundCog();
		path = Screenshots.takeScreenshot(driver, "Around Cognizant");
		Assert.assertEquals(AroundCogn,"Around Cognizant");	
	}

	@Test(priority=3)
	public void CountNews() throws InterruptedException {	
	WebElement five =driver.findElement(By.xpath("//*[@id=\"CaptionElementView\"]"));
		Sr.Scroll(five);
		Thread.sleep(3000);
		List<WebElement> CountNews = Fp.FirstNews();
		System.out.println("News present in first page are : ");
		for(int i =0;i<CountNews.size();i++) 
		{
			String newsname=CountNews.get(i).getText();
			System.out.println(newsname);	
			firstNews.add(newsname);
		}
	}

	@Test(priority = 4)
	public void VerifyTooltip() throws  IOException{
		List<WebElement> NEWS = Fp.ToolTips();
	     for(int j=0;j<NEWS.size();j++) {
	    	 String tooltip =NEWS.get(j).getText();
	    	 System.out.println(j+1+":"+NEWS.get(j).getText());
	    	 String actualtooltip = NEWS.get(j).getAttribute("title");
	    	 System.out.println("Actual Title of Tool Tip : "+tooltip);
	    	 if(actualtooltip.equals(tooltip)) { 
				   	System.out.println("Test Case Passed");
	    	 }
	     }
	}

	@Test(priority = 5)
	public void clickSeeAll() throws IOException {
		Sr.Scroll(Fp.SeeAll);
		Fp.ClickSeeAll();
		path = Screenshots.takeScreenshot(driver, "SEE All");
	}

	@Test(priority = 6, dependsOnMethods= {"clickSeeAll"})
	public void SecondPage() throws InterruptedException, IOException
	{
		Thread.sleep(5000);
		Sp = new SecondPage(driver);
		List<WebElement> SecondNews = Sp.SecondNews();
		path = Screenshots.takeScreenshot(driver, "SECOND PAGE");
		System.out.println("First 5 News Headers of Second Page are : ");
	    for(int k=0;k<5;k++) {
	    	 String SecondPageNews =SecondNews.get(k).getText();
	    	 secondNews.add(SecondPageNews);
	    	 System.out.println(k+1+":"+SecondPageNews);
	     }
}

	@Test(priority = 7, dependsOnMethods= {"clickSeeAll","SecondPage"})
	public void VerifyNews() {
	     for(int i=0;i<5;i++) {
	    	 for(int j=0;j<secondNews.size();j++) {
	    	 if(firstNews.get(i).equals(secondNews.get(j))){
	    		 System.out.println("News no :"+(i+1)+ " in First page is same as News No :"+(j+1)+" in Second page");
	    	 }
	    	 else {
	    		 System.out.println("News no :"+(i+1)+ " in First page is Not same as News No :"+(j+1)+" in Second page");
	    	 	}
	    	 }
	     }
	}
	
	
	@Test(priority =8)
	public void printNews() throws InterruptedException, FileNotFoundException,InterruptedException, IOException {
		for(int i=1; i<=5; i++) {
			WebElement SecondNews = driver.findElement(By.xpath("(//a[@data-automation-id='newsItemTitle'][1])["+i+"]"));
			String header=SecondNews.getAttribute("aria-label");
				
			Thread.sleep(1000);
			SecondNews.click();
			List<WebElement> ls;
			if(header.contains("CEO blog")) {
			ls = driver.findElements(By.xpath("//*[@id=\"c6c4fb50-d4a7-4791-81f8-a4f069ac45f3\"]/div/div/div/p[2]/span"));
			}
			else {
				ls = driver.findElements(By.xpath("//*[@id=\"fa45f946-463e-428f-a84b-0bbbde09c3ba\"]/div/div[1]"));
			}
			List<String> newsDetails = new ArrayList<String>();
			for(int k=0;k<ls.size();k++) {
				newsDetails.add(ls.get(k).getText());
			}
			for(String details:newsDetails) {
				System.out.println(details);
				Thread.sleep(3000);
				
			}
			
		
			
//			//ExcelUtility.saveDataToExcel(newsDetails,firstNews,secondNews);
//			//ExcelUtility.closeExcel();
//			Thread.sleep(3000);
			ls.clear();
			newsDetails.clear();
			driver.navigate().to("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/_layouts/15/news.aspx");
			Thread.sleep(3000);
		}		
	}


	@AfterClass
	public void close() throws InterruptedException, IOException
	{
		//Thread.sleep(5000);
		driver.quit();
	}
}
 
 