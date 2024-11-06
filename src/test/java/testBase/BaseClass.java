package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {

	public static  WebDriver driver;
	
	public org.apache.logging.log4j.Logger logger;
	
	//to load properties values using properties class
	public Properties p;
	

	@BeforeClass(groups= {"regression","smoke"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
				//Loading config.properties file
				FileReader file = new FileReader("./src//test//resources//config.properties");
				//Initializing new properties class
				p = new Properties();
				p.load(file);
				
				
				//To initialize the current class for logging information into logs folder
				logger = LogManager.getLogger(this.getClass());
				
				//For parallel testing
				switch(br.toLowerCase())
				{
				case "chrome" : driver = new ChromeDriver();                break;
				case "edge" :   driver = new EdgeDriver();                  break;
				default :       System.out.println("Invalid browser name"); return;
				}
				
				driver.manage().deleteAllCookies();
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				
				//Reading url from properties file
				driver.get(p.getProperty("pageUrl"));
				driver.manage().window().maximize();
		

	}
	
	// Taking screenshot and store in the folder



	
	 @AfterClass(groups= {"regression","smoke"})
	 public void tearDown() { 
		 driver.close();
	 }
	public void takeScreenshot(String name) throws IOException {
				
				  TakesScreenshot tk = (TakesScreenshot)driver;
				  File source= tk.getScreenshotAs(OutputType.FILE);
				  File target = new File("C:\\Users\\2361526\\eclipse-workspace\\identifying_courses\\screenshots\\"+name+".png");
				  FileHandler.copy(source, target);
				  
				}
	
	
	//screenshot in extent report
	 public String captureScreen(String tname) throws IOException {
		 
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
				
			return targetFilePath;
	 
		}
}
