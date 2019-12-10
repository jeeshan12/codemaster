package testVagarant.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static testVagarant.automation.pageconstants.SearchPageConstants.*;

import java.util.concurrent.TimeUnit;

public class SearchPage {

	protected WebDriver driver;

	@FindBy(className = FLIGHT_SUMMARY_LABEL)
	public WebElement lblFlightSummary;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	public boolean isSummaryLabelPresnet() {
		return lblFlightSummary.isDisplayed();
	}

}
