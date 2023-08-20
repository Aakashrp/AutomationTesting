package pages;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
//import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

import base.Baseclass1;

public class Giftcards extends Baseclass1 {

	By Gcard = By.xpath("//*[@id=\'header\']/section/div/ul[2]/li[3]/a");
	By Bday = By.xpath("//*[@id=\'app-container\']/div/main/section/section[1]/ul/li[3]/img");
	By Rupees = By.xpath("//*[@id=\'app-container\']/div/main/section/section[2]/div/section[2]/div[1]/button[1]");
	By Month = By.xpath("//*[@id='app-container']/div/main/section/section[2]/div/section[2]/div[4]/select[1]");
	By Date = By.xpath("//*[@id='app-container']/div/main/section/section[2]/div/section[2]/div[4]/select[2]");
	By Nextbtn = By.xpath("//*[@id=\'app-container\']/div/main/section/section[2]/div/section[2]/button");
	By Name = By.xpath("//*[@id=\'ip_4036288348\']");
	By Receiver = By.xpath("//*[@id=\'ip_137656023\']");
	By Sname = By.xpath("//*[@id=\'ip_1082986083\']");
	By SEmail = By.xpath("//*[@id=\'ip_4081352456\']");
	By Phoneno = By.xpath("//*[@id=\'ip_2121573464\']");
	By Submit = By.xpath("//*[@id=\'app-container\']/div/main/section/section[3]/form/button");
	By Message = By.id("ip_137656023");
	By Address = By.name("customer_address");
	By Pincode = By.id("ip_567727260");

	/********************* Gift Cards *************************/

	public void scrollup() throws InterruptedException {
		// pageup
		JavascriptExecutor Scrool1 = (JavascriptExecutor) driver;
		Scrool1.executeScript("window.scrollBy(0,-400)", "");
		Thread.sleep(2000);
		driver.findElement(Gcard).click();// Click on GiftCard
		Thread.sleep(3000);
	}

	public void information() throws InterruptedException {
		// pagedown
		JavascriptExecutor Scrool2 = (JavascriptExecutor) driver;
		Scrool2.executeScript("window.scrollBy(0,800)", "");
		Thread.sleep(2000);
		driver.findElement(Bday).click();// click on Birthday and Anniversary
		Thread.sleep(2000);

		/****************** Customize Form ******************/
		//logger = report.createTest("Fill Customize gift card");

		driver.findElement(Rupees).click();// click on the amount
		Select month = new Select(driver.findElement(Month));// Month DropDown
		Thread.sleep(2000);
		month.selectByValue("10/2023");

		Select date = new Select(driver.findElement(Date));// Date DropDown
		date.selectByValue("12");
		Thread.sleep(3000);
		driver.findElement(Nextbtn).click();// click on next
		//logger.log(Status.PASS, "Form Filled Successfully");
		Thread.sleep(2000);
	}

	/******************
	 * Recipients Form
	 *
	 * @throws Exception
	 *******************/

	/*********************
	 * Accepting Values From ExcelSheet
	 *************************/

	public void formValues() throws Exception {

		//logger = report.createTest("Fill Recipient Form with one invalid input");
		File file = new File("src/main/resources/Info module.xlsx"); // To read the data from excel
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row = sheet.getRow(1);
		//fetching data from excel
		XSSFCell cell0 = row.getCell(0);
		String SName = cell0.getStringCellValue();
		XSSFCell cell1 = row.getCell(1);
		String Email = cell1.getStringCellValue();
		XSSFCell cell2 = row.getCell(2);
		String Yname = cell2.getStringCellValue();
		XSSFCell cell3 = row.getCell(3);
		String Yemail = cell3.getStringCellValue();
		DataFormatter formatter = new DataFormatter();
		XSSFCell cell4 = sheet.getRow(1).getCell(4);
		String Phno = formatter.formatCellValue(cell4);

		driver.findElement(Name).sendKeys(SName);// Recipient's Name
		driver.findElement(Receiver).sendKeys(Email);// Recipient's email
		driver.findElement(Sname).sendKeys(Yname);// Your name
		driver.findElement(SEmail).sendKeys(Yemail);// Your email
		driver.findElement(Phoneno).sendKeys(Phno);// mobile no
		driver.findElement(Address).sendKeys("ABC Residency FLAT 68");
		driver.findElement(Pincode).sendKeys("411014");
		
		
		Thread.sleep(2000);
		driver.findElement(Submit).click();

		//logger.log(Status.PASS, "Form Filled Successfully");
		Thread.sleep(3000);
	}

	/********************* Display The Error Message *************************/

	public void displayerror() throws InterruptedException, IOException {
		//logger = report.createTest("Capture and display the error message");
		takeScreenshot("Error");
		String message = driver.findElement(Message).getAttribute("validationMessage");
		System.out.println("***************************************");
		System.out.println("Error Message");
		System.out.println("***************************************");
		System.out.println(message);
		//logger.log(Status.PASS, "ScreenShot and Message Displayed Successfully");
		Thread.sleep(2000);

	}

}