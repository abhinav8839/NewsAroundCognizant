package Utilites;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
public class Screenshots {

        public static String takeScreenshot(WebDriver driver, String Name) throws IOException{
        	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	File target= new File("C:\\Users\\2320195\\Downloads\\INT2QEA23QE015_Group4_AroundCognizantNews\\AroundCognizantNews\\ScreenShots\\" +Name+".png");
        	FileUtils.copyFile(src, target);
        	return target.getAbsolutePath();
    }
}
 