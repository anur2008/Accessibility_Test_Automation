package com.matson.accessibility.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.matson.accessibility.common.BaseTest;

import matson_accessibility_core.CommonUtils;
import matson_accessibility_core.Wave_Eval_Page;

public class AxsbltyTest extends BaseTest {
	Wave_Eval_Page waveEvalPge = new Wave_Eval_Page();
	
	
	@Test(dataProvider="TestUrlProvider")
	public void validateAxsblty(String appUrl ) {
		try {
			waveEvalPge.launch();	
			waveEvalPge.enterUrl(appUrl);			
			waveEvalPge.clickSubmit();
			waveEvalPge.waitForPageToLoad();
			waveEvalPge.getWaveResults();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
