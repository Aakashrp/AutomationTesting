
package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import com.google.common.io.Files;

//import Utils.ExtentReportManager;

//import Utils.ExtentReportManager;

public class Baseclass1 {

	public static WebDriver driver;
	public static Properties prop;// property file object
	//public ExtentReports report = ExtentReportManager.getReportInstance();
	//public ExtentTest logger;// is used to log test steps onto the generated HTML report

	/***************** Invoking Browser *******************/

	@BeforeTest
	@Parameters({ "browser" })
	public void invokeBrowser(String br) {
		prop = new Properties();
		String userDir = System.getProperty("user.dir");
		try {
			prop.load(new FileInputStream("src/main/java/properties/config.properties"));
		} catch (Exception e) {
			System.out.println("error in file");
		}
		if (br.matches("firefox")) {
			System.setProperty("webdriver.gecko.driver", userDir + "\\src\\test\\Driver\\geckodriver.exe");
			driver = new FirefoxDriver(); // opens Firefox browser
		}
		if (br.matches("chrome")) {
			System.setProperty("webdriver.chrome.driver", userDir + "\\src\\test\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();// opens Chrome browser
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
	}

	/************** Taking ScreenShot ***************/
	public static void takeScreenshot(String fileName) throws IOException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(file, new File(System.getProperty("user.dir") + "/src/main" + fileName + ".jpg"));
	}

	/************** Printing O/P in excel sheet ***************/
	public static void excelWrite(Map<String, String> namePriceMap) throws Exception {
		File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\output.xlsx");
		FileInputStream fio = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fio);
		XSSFSheet sheet = wb.getSheet("Oput");
		int i = 0;
		for (Map.Entry<String, String> entry : namePriceMap.entrySet()) {
			XSSFRow row = sheet.createRow(i++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(entry.getValue());
		}
		fio.close();
		FileOutputStream fo = new FileOutputStream(f);
		wb.write(fo);
		fo.close();
	}

	/*************** Close Browser ****************/
	@AfterSuite
	public void close() {
		driver.quit();
		// report.flush();
	}
}
