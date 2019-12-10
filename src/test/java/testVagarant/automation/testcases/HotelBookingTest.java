package testVagarant.automation.testcases;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testVagarant.automation.basepage.BasePage;
import testVagarant.automation.pages.HotelPage;
import testVagarant.automation.testbase.TestBase;
import testVagarant.automation.utils.SeleniumUtil;

public class HotelBookingTest extends TestBase {

	/*
	 * @FindBy(linkText = "Hotels") private WebElement hotelLink;
	 * 
	 * @FindBy(id = "Tags") private WebElement localityTextBox;
	 * 
	 * @FindBy(id = "SearchHotelsButton") private WebElement searchButton;
	 * 
	 * @FindBy(id = "travellersOnhome") private WebElement travellerSelection;
	 */

	private BasePage basePage;

	private HotelPage hotelPage;

	@Test(dataProvider = "HotelData")
	public void shouldBeAbleToSearchForHotels(Map<String, String> map) {
		// setDriverPath();
		basePage = new BasePage(getDriver());

		basePage.navigateToUrl(System.getProperty("applicationUrl"));
		/*
		 * driver.get("https://www.cleartrip.com/"); hotelLink.click();
		 * 
		 * localityTextBox.sendKeys("Indiranagar, Bangalore");
		 * 
		 * new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
		 * searchButton.click();
		 */

		hotelPage = new HotelPage(getDriver());

		// clicking hotles link
		hotelPage.clickHotels();
		
		// entering the locality
		hotelPage.enterLocality(map.get("locality"));
		
		// selecting the locality
		hotelPage.selectLocality(map.get("hotelLocation"));
		
		// clicking day in calendar
		hotelPage.selectCheckInDate();
		
		hotelPage.selectCheckOutDate();
		
		// select travellers
		hotelPage.selectTravellers(map.get("travellers"));
		
		// click search button
		hotelPage.clickSearchButton();
		
		//Asserting the label
		Assert.assertTrue(hotelPage.isCityLabelExist());
	}

	@DataProvider(name = "HotelData")
	public Object[][] getDetails() {
		return SeleniumUtil.getDataFromJSON(SystemUtils.getUserDir() + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "data" + File.separator + "Hotel.json", "hotel");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
	}
}

/*
 * private void setDriverPath() { if (PlatformUtil.isMac()) {
 * System.setProperty("webdriver.chrome.driver", "chromedriver"); } if
 * (PlatformUtil.isWindows()) { System.setProperty("webdriver.chrome.driver",
 * "chromedriver.exe"); } if (PlatformUtil.isLinux()) {
 * System.setProperty("webdriver.chrome.driver", "chromedriver_linux"); } }
 * 
 * }
 */
