package testCases;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount link");
			hp.clickRegister();
			logger.info("Clicked on Register link");
			
			logger.info("Providing customer details...");
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			regPage.setFirstName(randomString().toUpperCase());
			regPage.setLastName(randomString().toUpperCase());
			regPage.setEmail(randomString()+"@gmail.com"); // randomly generate the email
			regPage.setTelephone(randomNumber());
			
			String password = randomAlphaNumeric();
			
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			
			regPage.setPrivacyPolicy();
			regPage.clickButton();
			
			logger.info("Validating expected message...");
			String cnfMsg = regPage.getconfirmationMsg();
			if(cnfMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				logger.error("Test failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
			//Assert.assertEquals(cnfMsg, "Your Account Has Been Created!!!");
		} catch(Exception e) {
			Assert.fail();
		}

		logger.info("***** Finished TC001_AccountRegistrationTest *****");
		
	}
	
}
