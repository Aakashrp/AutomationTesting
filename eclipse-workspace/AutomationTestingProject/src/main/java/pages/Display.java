package pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Baseclass1;
//import com.aventstack.extentreports.Status;

public class Display extends Baseclass1 {



	// By Studybtn=By.xpath("//*[@id='topnav_wrapper']/ul/li[6]/span");
	By Studybtn = By.xpath("/html/body/div[1]/header/div[2]/div/nav/div/ul/li[6]/span");
	By Bookshelves = By.xpath("//*[@id=\"topnav_wrapper\"]/ul/li[6]/div/div/ul/li[1]/ul/li[2]/a/span");
//	By Bookshelves=By.xpath("//*[@id='topnav_wrapper']/ul/li[6]/div/div/ul/li[3]/ul/li[1]/a");

	By Popup = By.xpath("//*[@id='authentication_popup']/div[1]/div/div[2]/a[1]");
	By Price = By.xpath("//*[@id='filters-form']/div[1]/div/div/ul/li[1]");
	By Slider = By.xpath(
			"//*[@id='filters-form']/div[1]/div/div/ul/li[1]/div[2]/div/div/ul/li[1]/div/div[2]/div[2]/div/div[2]/div");
	By Storage = By.xpath("//*[@id='filters-form']/div[1]/div/div/ul/li[2]");
	By Opentype = By.id("filters_storage_type_Open");
	By Exclude = By.name("filters[availability][]");
	By Pname = By.xpath("//span[@class='name']");
	By Amount = By.xpath("//*[@class='price-number']/span");


	/********************* Opening WebSite *************************/
	public void openUrl() throws InterruptedException {
//		logger = report.createTest("Opening the browser");
//		logger.log(Status.INFO, "Browser opened successfully");
		String baseUrl = prop.getProperty("url");
		driver.get(baseUrl);
		// explicit wait
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Studybtn));
	}

	/********************* Click On Bookshelves *************************/
	public void select() throws InterruptedException {
		WebElement t2 = driver.findElement(Studybtn);
		Actions s2 = new Actions(driver);
		s2.moveToElement(t2).build().perform();
		driver.findElement(Bookshelves).click();// click on study
		Thread.sleep(5000);
		driver.findElement(Popup).click();// close the popup
		Thread.sleep(2000);
	}

	public void filter() throws InterruptedException {
		WebElement target = driver.findElement(Price);//
		Actions s = new Actions(driver);
		s.moveToElement(target).build().perform();
		Thread.sleep(1000);
		WebElement slider = driver.findElement(Slider);
		Thread.sleep(1000);
		new Actions(driver).dragAndDropBy(slider, -203, -20).perform();
		Thread.sleep(2000);
		WebElement tgt = driver.findElement(Storage);//
		Actions s2 = new Actions(driver);
		s2.moveToElement(tgt).build().perform();
		Thread.sleep(2000);
		driver.findElement(Opentype).click();// storage type open
		Thread.sleep(3000);
		driver.findElement(Exclude).click();// exclude out of stock
		Thread.sleep(2000);
	}

	/*********************
	 * Display the name and price of first three bookshelves
	 *************************/
		public void details() throws Exception {
		//	logger = report.createTest("Display Name and Price");
			List<WebElement> title = driver.findElements(Pname);// Product name
			List<WebElement> prices = driver.findElements(Amount);// Product price
			System.out.println("***************************************");
			System.out.println("The first three displayed products are:");
			System.out.println("***************************************");
			Map<String, String> namePriceMap = new LinkedHashMap<String, String>();
			for (int i = 0; i < 3; i++) {



			namePriceMap.put(title.get(i).getText(), prices.get(i).getText());
			System.out.println(title.get(i).getText() + " ");
			System.out.println(prices.get(i).getText());
	//		logger.log(Status.PASS, "Name and Price displayed successfully");
			}
			Baseclass1.excelWrite(namePriceMap);
			Thread.sleep(2000);
			}
}
