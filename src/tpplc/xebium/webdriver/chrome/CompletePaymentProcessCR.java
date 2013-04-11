package tpplc.xebium.webdriver.chrome;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

public class CompletePaymentProcessCR {

	private WebDriver driver = new ChromeDriver();
	private String baseURL;

	private String searchItem;
	private String billEmail;
	private String paymentCardNo;
	private String cardExpMonth;
	private String cardExpYear;
	private String cardStartMonth;
	private String cardStartYear;
	private String password;
	private String issueNo;

	private String invalidCardNumberPrompt = "Your card number has failed our validity checks and appears to be incorrect. Please check and re-enter.";
	private String invalidCardExpiryDatePrompt = "The card has expired.";
	private String invalidCardStartDatePrompt = "The StartDate is in the future. The card is not yet valid.";


	// wait method
	public void waitFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// set methods
	public void setIssueNumber(String value) {
		issueNo = value;
	}

	public void setPassword(String value) {
		password = value;
	}

	public void setStartMonth(String value) {
		cardStartMonth = value;
	}

	public void setStartYear(String value) {
		cardStartYear = value;
	}

	public void setExpiryMonth(String value) {
		cardExpMonth = value;
	}

	public void setExpiryYear(String value) {
		cardExpYear = value;
	}

	public void setPaymentCardNumber(String value) {
		paymentCardNo = value;
	}

	public void setBillingEmail(String value) {
		billEmail = value;
	}

	public void setSearchItem(String value) {
		searchItem = value;
	}

	public void setBaseUrl(String value) {
		baseURL = value;
	}

	// action methods
	public void selectProductAndGoToDeliveryDetails(String item, int itemNo) {
		WebElement srchField = driver.findElement(By.id("header_2_SearchTerm"));
		srchField.clear();
		srchField.sendKeys(item);
		WebElement addToBasket = driver.findElement(By
				.id("header_2_buttonPost"));
		addToBasket.click();
		waitFor(1000);
		WebElement itemLink = driver.findElement(By
				.xpath(".//*[@id='ctl1']/section/section/section/div/article["
						+ itemNo + "]/div[1]/a"));
		itemLink.click();
		waitFor(2000);
		driver.findElement(By.id("body_1_content_0_ctl01_AddToBasket")).click();
		waitFor(2000);
		WebElement shopBskt = driver.findElement(By
				.linkText("YOUR BASKET (1)"));
		if (shopBskt.isDisplayed() == true) {
			shopBskt.click();
		} else {
			driver.findElement(By.id("body_1_content_0_ctl01_AddToBasket")).click();
			waitFor(2000);
		}
		driver.findElement(
				By.xpath("/html/body/form/div[3]/div/div[2]/section/header/a"))
				.click();
	}

	public void CompleteDeliveryDetails() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.findElement(By.id("body_1_content_1_fldDelivery_Firstname"))
				.sendKeys("firstname");
		driver.findElement(By.id("body_1_content_1_fldDelivery_Surname"))
				.sendKeys("lastname");
		driver.findElement(By.id("body_1_content_1_fldDelivery_Telephone"))
				.sendKeys("01162111333");
		driver.findElement(
				By.id("body_1_content_1_postcodeLookup_manualEntryLink"))
				.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_Address1")).sendKeys("1 Address One");
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_Town")).sendKeys("Test Town");
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_County")).sendKeys("Test County");
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_Postcode")).sendKeys("LE2 4TG");
		driver.findElement(By.id("body_1_content_1_btnContinue")).click();
	}

	public void CompleteBillingAddress(String billingEmail) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("fld_Email")).sendKeys(billingEmail);
		driver.findElement(By.id("body_1_content_1_fld_Firstname")).sendKeys(
				"firstname");
		driver.findElement(By.id("body_1_content_1_fld_Surname")).sendKeys(
				"lastname");
		driver.findElement(By.id("body_1_content_1_fld_Telephone")).sendKeys(
				"01162111333");
		driver.findElement(
				By.id("body_1_content_1_postcodeLookup_manualEntryLink"))
				.click();
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_Address1")).sendKeys("1 Address One");
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_Town")).sendKeys("Test Town");
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_County")).sendKeys("Test County");
		driver.findElement(By.id("body_1_content_1_postcodeLookup_fld_Postcode")).sendKeys("LE2 4TG");
		driver.findElement(By.id("body_1_content_1_btnContinue")).click();
	}

	public void CompletePaymentDetailsWithIssueNo(String cardNo, String issue,
			String month, String year) {
		driver.switchTo().frame("body_1_content_1_SagePayIFrame");
		driver.findElement(By.name("cardnumber")).sendKeys(cardNo);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Select(driver.findElement(By.name("expirymonth")))
				.selectByVisibleText(month);
		new Select(driver.findElement(By.name("expiryyear")))
				.selectByVisibleText(year);
		driver.findElement(By.name("cardissue")).clear();
		driver.findElement(By.name("cardissue")).sendKeys(issue);
		driver.findElement(By.name("securitycode")).clear();
		driver.findElement(By.name("securitycode")).sendKeys("123");
		driver.switchTo().activeElement();
		WebElement prc = driver.findElement(By.id("proceedButton"));
		prc.sendKeys("\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).sendKeys(password);
		WebElement sbmt = driver
				.findElement(By
						.xpath(".//*[@id='AutoNumber1']/tbody/tr[2]/td/form/table/tbody/tr[6]/td[2]/input"));
		sbmt.sendKeys("\n");
	}

	public void CompletePaymentDetailsWithoutIssueNo(String cardNo,
			String month, String year) {
		driver.switchTo().frame("body_1_content_1_SagePayIFrame");
		driver.findElement(By.name("cardnumber")).sendKeys(cardNo);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Select(driver.findElement(By.name("expirymonth")))
				.selectByVisibleText(month);
		new Select(driver.findElement(By.name("expiryyear")))
				.selectByVisibleText(year);
		driver.findElement(By.name("securitycode")).clear();
		driver.findElement(By.name("securitycode")).sendKeys("123");
		driver.switchTo().activeElement();
		WebElement prc = driver.findElement(By.id("proceedButton"));
		prc.sendKeys("\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.name("password")).click();
		driver.findElement(By.name("password")).sendKeys(password);
		WebElement sbmt = driver
				.findElement(By
						.xpath(".//*[@id='AutoNumber1']/tbody/tr[2]/td/form/table/tbody/tr[6]/td[2]/input"));
		sbmt.sendKeys("\n");
	}

	public void CompletePaymentDetailsInvalidDataWithIssueNo(String cardNo,
			String strtMonth, String strtYear, String issue, String month,
			String year) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.switchTo().frame("body_1_content_1_SagePayIFrame");
		driver.findElement(By.name("cardnumber")).sendKeys(cardNo);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Select(driver.findElement(By.name("expirymonth")))
				.selectByVisibleText(strtMonth);
		new Select(driver.findElement(By.name("expiryyear")))
				.selectByVisibleText(strtYear);
		new Select(driver.findElement(By.name("expirymonth")))
				.selectByVisibleText(month);
		new Select(driver.findElement(By.name("expiryyear")))
				.selectByVisibleText(year);
		driver.findElement(By.name("cardissue")).clear();
		driver.findElement(By.name("cardissue")).sendKeys(issue);
		driver.findElement(By.name("securitycode")).clear();
		driver.findElement(By.name("securitycode")).sendKeys("123");
		driver.switchTo().activeElement();
		WebElement prc = driver.findElement(By.id("proceedButton"));
		prc.sendKeys("\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void CompletePaymentDetailsInvalidDataWithoutIssueNo(String cardNo,
			String strtMonth, String strtYear, String month, String year) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		driver.switchTo().frame("body_1_content_1_SagePayIFrame");
		driver.findElement(By.name("cardnumber")).sendKeys(cardNo);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		new Select(driver.findElement(By.name("startmonth")))
				.selectByVisibleText(strtMonth);
		new Select(driver.findElement(By.name("startyear")))
				.selectByVisibleText(strtYear);
		new Select(driver.findElement(By.name("expirymonth")))
				.selectByVisibleText(month);
		new Select(driver.findElement(By.name("expiryyear")))
				.selectByVisibleText(year);
		driver.findElement(By.name("securitycode")).clear();
		driver.findElement(By.name("securitycode")).sendKeys("123");
		driver.switchTo().activeElement();
		WebElement prc = driver.findElement(By.id("proceedButton"));
		prc.sendKeys("\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// confirmation methods
	public boolean CheckTitleAfterSuccessfulPayment() {
		boolean completed = false;
		boolean title = driver.getTitle().contentEquals("Your Order");
		if (title == true) {
			completed = true;
		}
		return completed;
	}

	public boolean CheckTitleAndErrorPromptForInvalidCardNo() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean completed = false;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement invalidCardPrompt = driver.findElement(By
				.xpath(".//*[@id='formCardDetails']/div/span"));
		String txtPrompt = invalidCardPrompt.getText();
		boolean promptIsVisible = txtPrompt
				.contentEquals(invalidCardNumberPrompt);
		boolean title = driver.getTitle().contentEquals("Payment Details");
		if (title == true && promptIsVisible == true) {
			completed = true;
		}
		return completed;
	}

	public boolean CheckTitleAndErrorPromptForInvalidExpiryDate() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean completed = false;
		WebElement invalidCardPrompt = driver.findElement(By
				.xpath(".//*[@id='formCardDetails']/div/span"));
		String txtPrompt = invalidCardPrompt.getText();
		boolean promptIsVisible = txtPrompt
				.contentEquals(invalidCardExpiryDatePrompt);
		boolean title = driver.getTitle().contentEquals("Payment Details");
		if (title == true && promptIsVisible == true) {
			completed = true;
		}
		return completed;
	}

	public boolean CheckTitleAndErrorPromptForInvalidStartDate() {
		boolean completed = false;
		WebElement invalidCardPrompt = driver.findElement(By
				.xpath(".//*[@id='formCardDetails']/div/span"));
		String txtPrompt = invalidCardPrompt.getText();
		boolean promptIsVisible = txtPrompt
				.contentEquals(invalidCardStartDatePrompt);
		boolean title = driver.getTitle().contentEquals("Payment Details");
		if (title == true && promptIsVisible == true) {
			completed = true;
		}
		return completed;
	}

	// methods called in Xebium
	public boolean PaymentProcessCompletedWithMaestro() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsWithIssueNo(paymentCardNo, issueNo, cardExpMonth,
				cardExpYear);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.getTitle().contentEquals("Your Order");
	}

	public boolean PaymentProcessCompletedNonMaestro() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsWithoutIssueNo(paymentCardNo, cardExpMonth,
				cardExpYear);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver.getTitle().contentEquals("Your Order");
	}

	public boolean PaymentProcessFailedForInvalidMaestroCardNumber() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsInvalidDataWithIssueNo(paymentCardNo,
				cardStartMonth, cardStartYear, issueNo, cardExpMonth,
				cardExpYear);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CheckTitleAndErrorPromptForInvalidCardNo();
	}

	public boolean PaymentProcessFailedForInvalidCardNumber() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsInvalidDataWithoutIssueNo(paymentCardNo,
				cardStartMonth, cardStartYear, cardExpMonth, cardExpYear);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CheckTitleAndErrorPromptForInvalidCardNo();
	}

	public boolean PaymentProcessFailedWithMaestroInvalidStartDate() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsInvalidDataWithIssueNo(paymentCardNo,
				cardStartMonth, cardStartYear, issueNo, cardExpMonth,
				cardExpYear);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CheckTitleAndErrorPromptForInvalidStartDate();
	}

	public boolean PaymentProcessFailedWithInvalidStartDate() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsInvalidDataWithoutIssueNo(paymentCardNo,
				cardStartMonth, cardStartYear, cardExpMonth, cardExpYear);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CheckTitleAndErrorPromptForInvalidStartDate();
	}

	public boolean PaymentProcessFailedWithMaestroInvalidExpiryDate() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsInvalidDataWithIssueNo(paymentCardNo,
				cardStartMonth, cardStartYear, issueNo, cardExpMonth,
				cardExpYear);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CheckTitleAndErrorPromptForInvalidExpiryDate();
	}

	public boolean PaymentProcessFailedWithInvalidExpiryDate() {
		driver.get(baseURL + "/");
		selectProductAndGoToDeliveryDetails(searchItem, 1);
		CompleteDeliveryDetails();
		CompleteBillingAddress(billEmail);
		CompletePaymentDetailsInvalidDataWithoutIssueNo(paymentCardNo,
				cardStartMonth, cardStartYear, cardExpMonth, cardExpYear);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return CheckTitleAndErrorPromptForInvalidExpiryDate();
	}

}
