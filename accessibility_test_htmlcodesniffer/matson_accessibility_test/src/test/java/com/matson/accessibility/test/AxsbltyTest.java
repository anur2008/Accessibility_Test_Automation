package com.matson.accessibility.test;

import org.testng.annotations.Test;

import com.matson.accessibility.test.common.BaseTest;

import matson_accessibility_core.HtmlSnifferFrame;

public class AxsbltyTest extends BaseTest {
	HtmlSnifferFrame snfFrame = new HtmlSnifferFrame();

	@Test
	public void codeSnifferTest() {
		try {
			snfFrame.launch();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
