package testVagarant.automation.pages;

import static testVagarant.automation.pageconstants.FlightBookingPageConstants.ADULTS_DROPDOWN;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.CALENDAR_ICON;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.CHILDREN_DROPDOWN;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.FROM_LIST;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.FROM_TEXTBOX;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.INFANTS_DROPDOWN;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.ONEWAY_CALENDAR_TABLE;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.ONE_WAY_CHECKBOX;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.SEARCH_BUTTON;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.TO_LIST;
import static testVagarant.automation.pageconstants.FlightBookingPageConstants.TO_TEXTBOX;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testVagarant.automation.utils.SeleniumUtil;

public class FlightBookingPage {

	protected WebDriver driver;

	@FindBy(id = ONE_WAY_CHECKBOX)
	private WebElement chkOnwWay;

	@FindBy(id = FROM_TEXTBOX)
	private WebElement txtFrom;

	@FindBy(id = TO_TEXTBOX)
	private WebElement txtTo;

	@FindBy(xpath = CALENDAR_ICON)
	private WebElement iconCalendar;

	@FindBy(id = ADULTS_DROPDOWN)
	private WebElement ddlAdults;

	@FindBy(id = CHILDREN_DROPDOWN)
	private WebElement ddlChildren;

	@FindBy(id = INFANTS_DROPDOWN)
	private WebElement ddlInfants;

	@FindBy(id = SEARCH_BUTTON)
	private WebElement btnSearch;

	@FindBy(xpath = FROM_LIST)
	private WebElement listFrom;

	@FindBy(xpath = TO_LIST)
	private WebElement listTo;

	@FindBy(xpath = ONEWAY_CALENDAR_TABLE)
	private List<WebElement> tblOnewayCalendar;

	public FlightBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	public FlightBookingPage clickOneWayCheckBox() {
		SeleniumUtil.selectCheckbox(chkOnwWay);
		return this;
	}

	public FlightBookingPage clickCalendarIcon() {
		SeleniumUtil.clickElement(iconCalendar);
		return this;
	}

	public FlightBookingPage selectFromStation(final String fromStation) {
		txtFrom.clear();
		SeleniumUtil.enterDataIntoTextBox(txtFrom, fromStation);
		// Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
		Awaitility.await("wait for list to appear").atMost(4, TimeUnit.SECONDS).until(() -> listFrom.isDisplayed());
		List<WebElement> list = listFrom.findElements(By.tagName("a"));
		list.stream().filter(x -> x.getText().contains(fromStation)).findFirst().get().click();
		return this;
	}

	public FlightBookingPage selectToStation(final String toStation, final String station) {
		txtTo.clear();
		SeleniumUtil.enterDataIntoTextBox(txtTo, toStation);
		// Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
		Awaitility.await("wait for list to appear").atMost(4, TimeUnit.SECONDS).until(() -> listTo.isDisplayed());
		List<WebElement> list = listTo.findElements(By.tagName("li"));
		list.stream().filter(x -> x.getText().contains(station)).findFirst().get().click();
		return this;
	}

	public FlightBookingPage selectAdultsFromDropdown(final String valueToSelect) {
		SeleniumUtil.selectDataFromDropdown(ddlAdults, valueToSelect);
		return this;
	}

	public FlightBookingPage selectChildrenFromDropdown(final String valueToSelect) {
		SeleniumUtil.selectDataFromDropdown(ddlChildren, valueToSelect);
		return this;
	}

	public FlightBookingPage selectInfantFromDropdown(final String valueToSelect) {
		SeleniumUtil.selectDataFromDropdown(ddlInfants, valueToSelect);
		return this;
	}

	public FlightBookingPage clickSearchFlights() {
		SeleniumUtil.clickElement(btnSearch);
		return this;
	}
	
	public FlightBookingPage clickDayInOneWayCalendar() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int day = calendar.get(Calendar.DATE);
		tblOnewayCalendar.stream().filter(d -> d.getText().equals(String.valueOf(day))).findFirst().get().click();
		return this;
	}
}
