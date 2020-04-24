package com.cl.tests;

import java.io.File;
import java.util.HashMap;
import org.openqa.selenium.support.ui.Select;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;

public class TestSuit1 {
	public static HashMap<String, String> data;
	
	@Test
	public void test() throws Exception {
	loadTestData("tc02");
	WebDriverManager.chromedriver().setup();	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.cleartrip.com/");
	
	String triptype=data.get("triptype");
	switch(triptype) {
	case "oneway":
	driver.findElement(By.xpath("//strong[contains(text(),'One way')]")).click();
	break;
	case "roundtrip":
		driver.findElement(By.xpath("//strong[contains(text(),'Round trip')]")).click();
	break;
		case "multicity":
		driver.findElement(By.xpath("//strong[contains(text(),'Multi-city')]")).click();
	break;
	default:
		System.out.println("please enter valid trip type");
		break;
		
	}
	//driver.quit();
		
	driver.findElement(By.xpath("//input[@id='FromTag']")).sendKeys(data.get("fromloc"));
driver.findElement(By.xpath("//input[@id='ToTag']")).sendKeys(data.get("toloc"));
driver.findElement(By.xpath("//div[contains(@class,'searchForm clearFix')]//div[1]//dl[1]//dd[1]//div[1]//a[1]//i[1]")).click();
driver.findElement(By.xpath("//div[contains(@class,'monthBlock first')]//a[contains(@class,'ui-state-default')][contains(text(),'"+data.get("date")+"')]")).click();
new Select(driver.findElement(By.xpath("//select[@id='Adults']"))).selectByVisibleText(data.get("adults"));
driver.findElement(By.xpath("//input[@id='SearchBtn']")).click();
}

	public void loadTestData(String testcasename) throws Exception{
		data=new HashMap<String, String>();
String path="D:\\10am batch\\ClearTrip\\src\\test\\resources\\testdata\\Book.xls";	
File file=new File(path);
Workbook wb=Workbook.getWorkbook(file);
Sheet s=wb.getSheet(0);
int rows=s.getRows();
int cols=s.getColumns();

	int testrow=0;
	loop:for(int i=0;i<rows;i++){
		String test=s.getCell(0,i).getContents();
		if(test.equals(testcasename)){
			testrow=i;
			break loop;
			
		}
		
	}
	for(int j=0;j<cols;j++){
		String key=s.getCell(j, 0).getContents();
		String value=s.getCell(j, testrow).getContents();
		data.put(key, value);
	
	}
	System.out.println(data);
	}


}

