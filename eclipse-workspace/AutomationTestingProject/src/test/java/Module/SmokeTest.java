package Module;

import org.testng.annotations.Test;

import base.Baseclass1;
import pages.Collection;
import pages.Display;
import pages.Giftcards;

public class SmokeTest extends Baseclass1 {

	Display ds= new Display();
	Collection co= new Collection();
	Giftcards gt= new Giftcards();
	
	@Test(priority=1)
	public void testOne() throws Exception {
		ds.openUrl();
		ds.select();
		ds.filter();
		ds.details();
	}
	@Test(priority=2)
	public void testTwo() throws InterruptedException {
		co.subMenu();
		co.beingPrint();
	}
	
	@Test(priority=3)
	public void testThree() throws Exception {
		gt.scrollup();
		gt.information();
		gt.formValues();
		gt.displayerror();
	}
}
