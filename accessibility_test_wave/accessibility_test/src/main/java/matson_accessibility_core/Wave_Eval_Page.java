package matson_accessibility_core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Wave_Eval_Page extends BasePage {

	CommonUtils cmmnUtils = new CommonUtils();

	private WebElement getErrElmnt() {
		return findElement(getLocatorValue("wave.error.count"));
	}

	private WebElement getSubmitBtn() {
		return findElement(getLocatorValue("wave.submit.button"));
	}

	private WebElement getReport() {
		return findElement(getLocatorValue("view.report"));
	}
	
	private List<WebElement> getReports() {
        return findElements(getLocatorValue("wave.results"));
    }
 

	private WebElement getReportMsg() {
		return findElement(getLocatorValue("report.message.text"));
	}

	private WebElement getUrlInput() {
		return findElement(getLocatorValue("wave.eval.page.inputUrl"));
	}

	public void getErrCount() {
		String Err = getErrElmnt().getText();
		System.out.println("Err:" + Err);

	}

	public void enterUrl(String appUrl) throws IOException {
		 getUrlInput().clear();
		getUrlInput().sendKeys(appUrl);
		System.out.println("Wave has detected the following for " + appUrl);
		
	}

	public String readReport() {
		getReport().click();
		String reportMsg = getReportMsg().getText();
		System.out.println("reportMsg:" + reportMsg);
		return reportMsg;

	}

	public void launch() throws Exception {

		final String testUrl;
		AppContext.getInstance().loadDefaultProperties();
		testUrl = AppContext.getInstance().getApplicationUrl();
		go(testUrl);
		ApplicationLog.info("AppUrl:" + testUrl);
	}


	public void getWaveResults() {
        List<WebElement> reports = getReports();
        for (WebElement report : reports) {
            
            System.out.println(
                    "There are " + report.getText() + " identified in the page" );
 
        }
    }
	
	
	public void clickSubmit() {
		getSubmitBtn().click();
	}
//	
	@Override
	public boolean isPageLoaded() {
		return isElementDisplayed(getLocatorValue("wave.error.count"));

	}

	@Override
	public void waitForPageToLoad() {
		waitForElementVisible(getLocatorValue("wave.matson.page.load"));

	}
}
