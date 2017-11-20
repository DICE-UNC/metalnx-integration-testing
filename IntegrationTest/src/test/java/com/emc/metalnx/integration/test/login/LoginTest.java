package com.emc.metalnx.integration.test.login;

import java.util.Properties;

import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emc.metalnx.test.generic.UiTestUtilities;

public class LoginTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
	private static Properties testingProperties = new Properties();
	public WebDriver driver = null;

	@BeforeClass
	public void beforeClass() throws Exception {
		org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();
		UiTestUtilities.init();
		driver = UiTestUtilities.getDriver();

	}

	/**
	 * After all tests are done, the test must quit the driver. This will close
	 * every window associated with the current driver instance.
	 */
	@AfterClass
	public void afterClass() {
		if (driver != null) {
			driver.quit();
			driver = null;
			UiTestUtilities.setDriver(null);
		}
	}

	/**
	 * Tests a valid username and password for login, and checks if it moves to the
	 * dashboard page
	 *
	 * @throws Exception
	 */
	@Test
	public void testValidUsernameAndPasswordForLogin() {
		logger.info("Testing valid username and password for login");
		UiTestUtilities.login(UiTestUtilities.testingProperties.getProperty(TestingPropertiesHelper.IRODS_USER_KEY),
				UiTestUtilities.testingProperties.getProperty(TestingPropertiesHelper.IRODS_PASSWORD_KEY));

		// check if after login, the user is redirected to the dashboard page
		Assert.assertEquals(UiTestUtilities.DASHBOARD_URL, driver.getCurrentUrl());
		UiTestUtilities.logout();
	}
	
	/**
	 * Tests an invalid username and password for login and checks if an error is
	 * shown
	 *
	 * @throws Exception
	 */
	@Test
	public void testInvalidUsernameAndPasswordForLogin() throws Exception {
		logger.info("Testing invalid username and password for login");
		UiTestUtilities.login("ThisIsAnInvalidUsername", "ThisIsAnInvalidPassword");

		/*
		 * There is no error message for invalid login WebElement errorMsg =
		 * driver.findElement(By.className("errorMsg")); check if after entering invalid
		 * login credentials (username and password), an error message is shown
		 */
		Assert.assertEquals(UiTestUtilities.LOGINERROR_URL, driver.getCurrentUrl());
	}
	
	
}
