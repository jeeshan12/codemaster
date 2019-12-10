package testVagarant.automation.pages;

import static testVagarant.automation.pageconstants.HotelPageConstants.CHECKIN_DATE_ICON;
import static testVagarant.automation.pageconstants.HotelPageConstants.CHECKIN_DATE_TABLE;
import static testVagarant.automation.pageconstants.HotelPageConstants.CHECKOUT_DATE_TABLE;
import static testVagarant.automation.pageconstants.HotelPageConstants.CITY_LABEL;
import static testVagarant.automation.pageconstants.HotelPageConstants.HOTELS_LINK;
import static testVagarant.automation.pageconstants.HotelPageConstants.HOTELS_LIST;
import static testVagarant.automation.pageconstants.HotelPageConstants.SEARCH_HOTELS_LIST;
import static testVagarant.automation.pageconstants.HotelPageConstants.TRAVELLERS_DROPDOWN_LIST;
import static testVagarant.automation.pageconstants.HotelPageConstants.WHERE_TEXTBOX;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.util.concurrent.Uninterruptibles;

import testVagarant.automation.utils.SeleniumUtil;

public class HotelPage {
	protected WebDriver driver;

	public HotelPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@FindBy(linkText = HOTELS_LINK)
	private WebElement lnkHotel;

	@FindBy(id = WHERE_TEXTBOX)
	private WebElement txtLocality;

	@FindBy(id = SEARCH_HOTELS_LIST)
	private WebElement btnSearch;

	@FindBy(id = TRAVELLERS_DROPDOWN_LIST)
	private WebElement ddlTravellerOnHome;

	@FindBy(xpath = CHECKIN_DATE_TABLE)
	private List<WebElement> tblCheckIn;

	@FindBy(xpath = CHECKOUT_DATE_TABLE)
	private List<WebElement> tblCheckOut;

	@FindBy(xpath = CHECKIN_DATE_ICON)
	private WebElement iconCheckIn;

	@FindBy(xpath = HOTELS_LIST)
	private List<WebElement> lstHotels;

	@FindBy(xpath = CITY_LABEL)
	public WebElement lblCity;

	public HotelPage selectCheckInDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int day = calendar.get(Calendar.DATE);
		tblCheckIn.stream().filter(d -> d.getText().equals(String.valueOf(day))).findFirst().get().click();
		return this;
	}

	public HotelPage selectCheckOutDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int day = calendar.get(Calendar.DATE);
		tblCheckOut.stream().filter(d -> d.getText().equals(String.valueOf(day + 2))).findFirst().get().click();
		return this;
	}

	public HotelPage clickHotels() {
		SeleniumUtil.clickElement(lnkHotel);
		this.driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		return this;
	}

	public HotelPage clickSearchButton() {
		SeleniumUtil.clickElement(btnSearch);
		return this;
	}

	public HotelPage enterLocality(final String locality) {
		SeleniumUtil.enterDataIntoTextBox(txtLocality, locality);
		return this;
	}

	public HotelPage selectTravellers(final String locality) {
		SeleniumUtil.selectDataFromDropdown(ddlTravellerOnHome, locality);
		return this;
	}

	public HotelPage clickCheckInIcon() {
		SeleniumUtil.clickElement(iconCheckIn);
		return this;
	}

	public HotelPage selectLocality(final String location) {
		Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
		for (WebElement element : lstHotels) {
			if (element.getText().equalsIgnoreCase(location)) {
				element.click();
				break;
			}
		}
		return this;
	}

	public boolean isCityLabelExist() {
		return lblCity.isDisplayed();
	}
}
