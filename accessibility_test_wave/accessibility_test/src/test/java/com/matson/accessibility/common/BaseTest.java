package com.matson.accessibility.common;



import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import matson_accessibility_core.ApplicationLog;
import matson_accessibility_core.CommonUtils;
import matson_accessibility_core.DriverFactory;

	
	public abstract class BaseTest{			
			
		CommonUtils commnUtils = new CommonUtils();
		@DataProvider(name ="TestUrlProvider")
		public Object[] getUrlFrmXl() throws Exception{
	         Object[] testObjArray = commnUtils.readExcel().toArray();
	         return (testObjArray);
			}

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



