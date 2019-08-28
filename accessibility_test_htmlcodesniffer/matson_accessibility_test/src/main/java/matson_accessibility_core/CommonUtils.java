package matson_accessibility_core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonUtils extends BasePage {

	public ArrayList readExcel() throws IOException {

ArrayList<String> cellValue=new ArrayList<String>();
		try {
			FileInputStream file = new FileInputStream(new File("src\\test\\resources\\matsondotcomUrls.xlsx"));
			// Get the workbook instance for XLS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					System.out.print(cell.getStringCellValue() + "\t\t");
				String cellVal=cell.getStringCellValue();
				cellValue.add(cellVal);

				}
				System.out.println("");
			}
			file.close();
			FileOutputStream out = new FileOutputStream(new File("src\\test\\resources\\test.xls"));
			workbook.write(out);
			out.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellValue;
		
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

//		String cellValue=null;
//
//		//Create an object of File class to open xlsx file
//		
//		File file = new File("src\\test\\resources\\pageUrl.xlsx");
//
//		//Create an object of FileInputStream class to read excel file
//
//		FileInputStream inputStream = new FileInputStream(file);
//
//		Workbook WBook = null;   
//
//
//		WBook = new XSSFWorkbook(inputStream);
//
//
//		//Read sheet inside the workbook by its name
//
//		Sheet sSheet = WBook.getSheetAt(0);
//		System.out.println("sheetname:"+sSheet.toString());
//
//		//Find number of rows in excel file
//
//		int rowCount = sSheet.getLastRowNum()-sSheet.getFirstRowNum();
//
//		//Create a loop over all the rows of excel file to read it
//
//		for ( int i = 0; i < rowCount+1; i++) {
//
//			Row row = sSheet.getRow(i);
//
//			//Create a loop to print cell values in a row
//
//			for (int j = 0; j < row.getLastCellNum(); j++) {
//				//
//				//	            //Print Excel data in console
//				//
//				//	            System.out.print(row.getCell(j).getStringCellValue());
//				
//				 cellValue= row.getCell(j).getStringCellValue();
//				
//				System.out.print("cellValue:"+cellValue);
//				go(cellValue);	
////           cellValNew=cellValue.split("[||]");
////            System.out.println("cellValNew:"+cellValNew);
//    		
//
//
//
//
//
//
//				WBook.close();
//
//			}

//	}
//		return cellValue;
//	}
