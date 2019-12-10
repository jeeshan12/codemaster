package testVagarant.automation.testcases;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import testVagarant.automation.basepage.BasePage;
import testVagarant.automation.pages.SignInPage;
import testVagarant.automation.testbase.TestBase;

public class SignInTest extends TestBase {

	private BasePage basePage;

	private SignInPage signInPage;

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

		// setDriverPath();

		basePage = new BasePage(getDriver());

		basePage.navigateToUrl(System.getProperty("applicationUrl"));

		// driver.get("https://www.cleartrip.com/");

		// waitFor(2000);

		signInPage = new SignInPage(getDriver());

		signInPage.clickYourTripsLabel();
		// driver.findElement(By.linkText("Your trips")).click();

		signInPage.clickSignInButton();
		// driver.findElement(By.id("SignIn")).click();

		signInPage.switchToSpecificFrame();
		// driver.switchTo().frame(driver.findElement(By.id("modal_window")));
		System.out.println(signInPage.getUserNameText());
		Awaitility.await("Wait for Log in with Facebook  to present").atMost(20, TimeUnit.SECONDS)
				.until(() -> signInPage.getUserNameText().contains("Log in with Facebook"));

		signInPage.clickSignInFormButton();

		// driver.findElement(By.id("signInButton")).click();

		// String errors1 = driver.findElement(By.id("errors1")).getText();
		Awaitility.await("There were errors in your submission to be present").atMost(20, TimeUnit.SECONDS)
				.until(() -> signInPage.getErrorMessage().contains("There were errors in your submission"));
		Assert.assertTrue(signInPage.getErrorMessage().contains("There were errors in your submission"));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
	}

	/*
	 * private void waitFor(int durationInMilliSeconds) { try {
	 * Thread.sleep(durationInMilliSeconds); } catch (InterruptedException e) {
	 * e.printStackTrace(); // To change body of catch statement use File | Settings
	 * | File Templates. } }
	 */

	/*
	 * private void setDriverPath() { if (PlatformUtil.isMac()) {
	 * System.setProperty("webdriver.chrome.driver", "chromedriver"); } if
	 * (PlatformUtil.isWindows()) { System.setProperty("webdriver.chrome.driver",
	 * "chromedriver.exe"); } if (PlatformUtil.isLinux()) {
	 * System.setProperty("webdriver.chrome.driver", "chromedriver_linux"); } }
	 */
}
