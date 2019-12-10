package testVagarant.automation.pages;

import static testVagarant.automation.pageconstants.SignInPageConstants.ERROR_LABEL;
import static testVagarant.automation.pageconstants.SignInPageConstants.SIGN_IN_BUTTON;
import static testVagarant.automation.pageconstants.SignInPageConstants.SIGN_IN_FORM_BUTTON;
import static testVagarant.automation.pageconstants.SignInPageConstants.LOGIN_FB_FORM_LABEL;
import static testVagarant.automation.pageconstants.SignInPageConstants.YOUR_TRIPS_LABEL;

import java.util.concurrent.TimeUnit;

import static testVagarant.automation.pageconstants.SignInPageConstants.FRAME_REFERENCE;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testVagarant.automation.utils.SeleniumUtil;

public class SignInPage {

	protected WebDriver driver;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}

	@FindBy(linkText = YOUR_TRIPS_LABEL)
	private WebElement lblYourTrips;

	@FindBy(id = SIGN_IN_BUTTON)
	private WebElement btnSignIn;

	@FindBy(id = SIGN_IN_FORM_BUTTON)
	private WebElement btnSignInForm;

	@FindBy(xpath = LOGIN_FB_FORM_LABEL)
	private WebElement lblFbLabel;
	
	@FindBy(id = ERROR_LABEL)
	private WebElement lblErrorMessage;
	
	@FindBy(id = FRAME_REFERENCE)
	private WebElement refFrame;

	public SignInPage clickYourTripsLabel() {

		SeleniumUtil.clickElement(lblYourTrips);
		return this;

	}

	public SignInPage clickSignInButton() {

		SeleniumUtil.clickElement(btnSignIn);
		return this;

	}

	public SignInPage clickSignInFormButton() {
		SeleniumUtil.clickElement(btnSignInForm);
		return this;

	}

	public String getUserNameText() {
		return SeleniumUtil.getROValue(lblFbLabel);
	}
	
	public String getErrorMessage() {
		return SeleniumUtil.getROValue(lblErrorMessage);
	}
	
	public void switchToSpecificFrame() {
		driver.switchTo().frame(refFrame);
	}

}
