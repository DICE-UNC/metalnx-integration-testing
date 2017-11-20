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

public class AdminLoginTest {
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginTest.class);

	private static WebDriver driver = null;

	private static Properties testingProperties = new Properties();

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
	 * Test for admin log in.
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoginAsAdmin() throws Exception {
		logger.info("Test logging in as a admin user");
		UiTestUtilities.login(
				UiTestUtilities.testingProperties.getProperty(TestingPropertiesHelper.IRODS_USER_KEY),
				UiTestUtilities.testingProperties.getProperty(TestingPropertiesHelper.IRODS_PASSWORD_KEY));
		Assert.assertEquals(UiTestUtilities.DASHBOARD_URL, driver.getCurrentUrl());
	}
}
