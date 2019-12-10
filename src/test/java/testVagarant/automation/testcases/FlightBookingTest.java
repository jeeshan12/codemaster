package testVagarant.automation.testcases;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

import testVagarant.automation.basepage.BasePage;
import testVagarant.automation.pages.FlightBookingPage;
import testVagarant.automation.pages.SearchPage;
import testVagarant.automation.testbase.TestBase;
import testVagarant.automation.utils.SeleniumUtil;

public class FlightBookingTest extends TestBase {
	/**
	 * {@link BasePage} reference
	 */
	private BasePage basePage;

	/**
	 * {@link SearchPage} reference
	 */
	private SearchPage searchPage;

	/**
	 * {@link FlightBookingPage} reference
	 */
	private FlightBookingPage flightage;

	@Test(dataProvider = "FlightData")
	public void testThatResultsAppearForAOneWayJourney(Map<String, String> map) {

		// setDriverPath();

		basePage = new BasePage(getDriver());
		// driver.get("https://www.cleartrip.com/");
		// navigating to the URL
		basePage.navigateToUrl(System.getProperty("applicationUrl"));
		// waitFor(2000);
		// driver.findElement(By.id("OneWay")).click();

		flightage = new FlightBookingPage(getDriver());

		// clicking one way checkbox
		flightage.clickOneWayCheckBox();

		// driver.findElement(By.id("FromTag")).clear();
		// driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

		// This method will enter the from station and select the value from smart
		// search . No need to wait for list to appear as it has been handled in the
		// method using awaitility
		flightage.selectFromStation(map.get("fromStation"));

		// wait for the auto complete options to appear for the origin

		/*
		 * waitFor(2000); List<WebElement> originOptions =
		 * driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		 * originOptions.get(0).click();
		 */

		/*
		 * driver.findElement(By.id("toTag")).clear();
		 * driver.findElement(By.id("toTag")).sendKeys("Delhi");
		 */
		// wait for the auto complete options to appear for the destination

		// waitFor(2000);
		// select the first item from the destination auto complete list
		/*
		 * List<WebElement> destinationOptions =
		 * driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		 * destinationOptions.get(0).click();
		 */

		// driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

		// This method will enter the to station and select the value from smart
		// search . No need to wait for list to appear as it has been handled in the
		// method using awaitility
		flightage.selectToStation(map.get("toStation"), map.get("toStationName"));

		// clicking day in calendar
		flightage.clickDayInOneWayCalendar();
		
		//selecting adults from dropdown
		flightage.selectAdultsFromDropdown(map.get("adults"));

		//selecting children from dropdown
		flightage.selectChildrenFromDropdown(map.get("children"));
		
		//selecting infants from dropdown
		flightage.selectInfantFromDropdown(map.get("infants"));

		// all fields filled in. Now click on search
		// driver.findElement(By.id("SearchBtn")).click();
		
		//clicking search flights
		flightage.clickSearchFlights();

		// waitFor(5000);

		searchPage = new SearchPage(getDriver());

		// waiting till the summary appears on Search page
		Predicate<WebElement> element = (e) -> {
			if (e.isDisplayed()) {
				return true;
			} else
				return false;
		};

		Awaitility.await("wait for the flight summary to appear on screen").atMost(20, TimeUnit.SECONDS)
				.until(() -> element.test(searchPage.lblFlightSummary));

		// verify that result appears for the provided journey search
		// Assert.assertTrue(isElementPresent(By.className("searchSummary")));

		Assert.assertTrue(searchPage.isSummaryLabelPresnet());

		// close the browser
		// driver.quit();

	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {
		getDriver().quit();
	}

	/*
	 * private void waitFor(int durationInMilliSeconds) { try {
	 * Thread.sleep(durationInMilliSeconds); } catch (InterruptedException e) {
	 * e.printStackTrace(); //To change body of catch statement use File | Settings
	 * | File Templates. } }
	 */

	@DataProvider(name = "FlightData")
	public Object[][] getDetails() {
		return SeleniumUtil.getDataFromJSON(SystemUtils.getUserDir() + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "data" + File.separator + "Flight.json", "flight");
	}

	/*
	 * private void setDriverPath() { if (PlatformUtil.isMac()) {
	 * System.setProperty("webdriver.chrome.driver", "chromedriver"); } if
	 * (PlatformUtil.isWindows()) { System.setProperty("webdriver.chrome.driver",
	 * "chromedriver.exe"); } if (PlatformUtil.isLinux()) {
	 * System.setProperty("webdriver.chrome.driver", "chromedriver_linux"); } }
	 */
}
