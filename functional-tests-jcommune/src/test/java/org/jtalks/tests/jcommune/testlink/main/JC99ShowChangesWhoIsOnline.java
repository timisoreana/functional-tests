package org.jtalks.tests.jcommune.testlink.main;

/**
 * @author masyan
 */
public class JC99ShowChangesWhoIsOnline {
	//TODO uncomment after repair sessions
//	int prevCount;
//	int prevCountGuests;
//	int prevCountRegistered;
//
//	@BeforeMethod(alwaysRun = true)
//	@Parameters({"app-url", "uUsername", "uPassword"})
//	public void setupCase(String appUrl, String username, String password) {
//		driver.get(appUrl);
//		signIn(username, password);
//		prevCount = new Integer(mainPage.getUsersOnlineCount().getText()).intValue();
//		prevCountRegistered = new Integer(mainPage.getRegisteredUsersOnlineCount().getText()).intValue();
//		prevCountGuests = new Integer(mainPage.getGuestsUsersOnlineCount().getText()).intValue();
//	}
//
//	@AfterMethod(alwaysRun = true)
//	@Parameters({"app-url"})
//	public void destroy(String appUrl) {
//		logOut(appUrl);
//		driver2.close();
//	}
//
//	@Test
//	@Parameters({"app-url"})
//	public void showChangesWhoIsOnlineTest(String appUrl) {
//		createSecondDriver();
//		driver2.get(appUrl);
//		MainPage mainPage2 = new MainPage(driver2);
//		int count = new Integer(mainPage2.getUsersOnlineCount().getText()).intValue();
//		int countGuests = new Integer(mainPage2.getGuestsUsersOnlineCount().getText()).intValue();
//		int countRegistered = new Integer(mainPage2.getRegisteredUsersOnlineCount().getText()).intValue();
//
//		assertTrue(prevCount + 1 == count);
//		assertTrue(prevCountRegistered == countRegistered);
//		assertTrue(prevCountGuests + 1 == countGuests);
//	}
}