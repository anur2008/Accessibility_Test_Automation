package com.matson.accessibility.test.common;



	import java.io.IOException;
	import java.lang.reflect.Method;

	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.testng.ITestContext;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Optional;
	import org.testng.annotations.Parameters;

import matson_accessibility_core.ApplicationLog;
import matson_accessibility_core.BasePage;
import matson_accessibility_core.DriverFactory;

	

	

	public abstract class BaseTest{			
			
		
		

		@AfterClass
		public void quitDriverAfterClass() {
			ApplicationLog.info("In @quitDriverAfterClass for thread id:"
					+ Thread.currentThread().getId());
			DriverFactory.getInstance().removeDriver();	
		}

		@AfterTest
		public void quitDriverAfterTest() {
			ApplicationLog.info("In @quitDriverAfterTest for thread id:"
					+ Thread.currentThread().getId());
			DriverFactory.getInstance().removeDriver();	
		}

		
	}



