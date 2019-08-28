package matson_accessibility_core;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HtmlSnifferFrame extends BasePage {
	
	CommonUtils cmmnUtils = new CommonUtils();
	
	private WebElement getErrElmnt() {
		return findElement(getLocatorValue("error.count.section"));
	}

	private WebElement getStandDrpDown() {
		return findElement(getLocatorValue("standard.dropdown"));
	}
	private WebElement getReport() {
		return findElement(getLocatorValue("view.report"));
	}
	private WebElement getReportMsg() {
		return findElement(getLocatorValue("report.message.text"));
	}

	public void getErrCount() {
		String Err = getErrElmnt().getText();
		System.out.println("Err:" + Err);

	}
	public String readReport() { 	
		getReport().click();		
		String reportMsg=getReportMsg().getText();
		System.out.println("reportMsg:"+reportMsg);
		return reportMsg;
		
	}

	public void launch() throws Exception {
		AppContext.getInstance().loadDefaultProperties();
		ArrayList<String> appUrls=new ArrayList<String>();
	 appUrls=cmmnUtils.readExcel();
	
//		System.out.println("applicationURLs1:"+applicationURLs1[0]);
	for (String appUrl:appUrls)
		{		
		go(appUrl);	
		testAccess();
		//selectStandard();
		//readReport();
			}
	}

	public void testAccess() {
				((JavascriptExecutor) getDriver()).executeScript(
				//"javascript:(function() {var _p='//squizlabs.github.io/HTML_CodeSniffer/build/';var _i=function(s,cb) {var sc=document.createElement('script');sc.onload = function() {sc.onload = null;sc.onreadystatechange = null;cb.call(this);};sc.onreadystatechange = function(){if(/^(complete|loaded)$/.test(this.readyState) === true){sc.onreadystatechange = null;sc.onload();}};sc.src=s;if (document.head) {document.head.appendChild(sc);} else {document.getElementsByTagName('head')[0].appendChild(sc);}}; var options={path:_p};_i(_p+'HTMLCS.js',function(){HTMLCSAuditor.run('WCAG2AA',null,options);});})();");
						"javascript:void(window.open('http://wave.webaim.org/report?url='+escape(window.location)))");

	}
	

	public void selectStandard() {
		try {
			System.out.println("CLICKED DROPDOWN");
			for (int i = 0; i <= 2; i++) {
				Select stdDrpdwn = new Select(getStandDrpDown());
				stdDrpdwn.selectByIndex(i);
				Thread.sleep(1000);
				getErrCount();
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean isPageLoaded() {
		return isElementDisplayed(getLocatorValue("error.count.section"));

	}
	@Override
	public void waitForPageToLoad() {
		waitForElementVisible(getLocatorValue("error.count.section"));

	}
}
