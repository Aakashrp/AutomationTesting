package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

//import com.aventstack.extentreports.Status;

import base.Baseclass1;

public class Collection extends Baseclass1 {

	By Icon=By.xpath("//*[@id=\'topnav_wrapper\']/ul/li[11]/span");
    //By Menu=By.xpath("//a[contains(text(),'New Arrivals')]");
    By Menu=By.xpath("//*[@id=\"topnav_wrapper\"]/ul/li[11]/div/div/ul/li[3]/div/a");
    By Submenu=By.xpath("//a[contains(text(),'New Arrivals')]/parent::*/following-sibling::ul/li");
	/********************* Collections *************************/

	public void subMenu() throws InterruptedException {
		// pageup
		JavascriptExecutor Scrool = (JavascriptExecutor) driver;
		Scrool.executeScript("window.scrollBy(0,-300)", "");
		driver.findElement(Icon).click();// click on collections

		Thread.sleep(2000);
		WebElement t1 = driver.findElement(Menu);// New Arrivals menu

		Actions s = new Actions(driver);
		s.moveToElement(t1).build().perform();
	}

	/********************* Display SubMenu Items *************************/

	public void beingPrint() throws InterruptedException {
		//logger = report.createTest("Display all submenu items");
		List<WebElement> subMenuItems = driver.findElements(Submenu);

		String[] items = new String[6];
		System.out.println("***************************************");
		System.out.println("Sub Menu Items");
		System.out.println("***************************************");
		for (int i = 0; i < 6; i++) {

			items[i] = subMenuItems.get(i).getText();
			System.out.println(subMenuItems.get(i).getText());
			//logger.log(Status.PASS, "Submenu items are displayed successfully");
			Thread.sleep(1000);

		}
	}

}